const API_BASE_URL = "http://localhost:8080";

const booksTableBody = document.getElementById("booksTableBody");
const booksLoading = document.getElementById("booksLoading");
const booksEmptyState = document.getElementById("booksEmptyState");
const btnRefresh = document.getElementById("btnRefresh");

const addBookForm = document.getElementById("addBookForm");
const addBookModalEl = document.getElementById("addBookModal");
const addBookModal = new bootstrap.Modal(addBookModalEl);

function getFirstDefined(obj, keys, defaultValue = "") {
  for (const k of keys) {
    if (obj && obj[k] !== undefined && obj[k] !== null) return obj[k];
  }
  return defaultValue;
}

function formatMoney(value) {
  if (value === undefined || value === null || value === "") return "";
  const num = Number(value);
  if (Number.isNaN(num)) return String(value);
  return num.toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}

function setLoading(isLoading) {
  booksLoading.classList.toggle("d-none", !isLoading);
}

function setEmptyState(isEmpty) {
  booksEmptyState.classList.toggle("d-none", !isEmpty);
}

async function fetchBooks() {
  setLoading(true);
  try {
    const resp = await fetch(`${API_BASE_URL}/api/books`, {
      method: "GET",
      headers: { "Accept": "application/json" }
    });

    if (!resp.ok) {
      const text = await resp.text().catch(() => "");
      throw new Error(`GET /api/books failed: ${resp.status} ${text}`.trim());
    }

    const books = await resp.json();
    // Expecting an array; if API returns an object, normalize to an array.
    const rows = Array.isArray(books) ? books : (books?.content ?? []);

    booksTableBody.innerHTML = "";
    setEmptyState(rows.length === 0);

    for (const book of rows) {
      const bookId = getFirstDefined(book, ["bookId", "id", "book_id"], "");
      const title = getFirstDefined(book, ["title", "name"], "");
      const author = getFirstDefined(book, ["author", "authorName", "author_name"], "");
      const price = formatMoney(getFirstDefined(book, ["price"], ""));
      const category = getFirstDefined(book, ["category", "genre"], "");

      const tr = document.createElement("tr");
      tr.innerHTML = `
        <td class="text-nowrap">${bookId}</td>
        <td>${title}</td>
        <td>${author}</td>
        <td class="text-nowrap">${price}</td>
        <td>${category}</td>
      `;

      booksTableBody.appendChild(tr);
    }
  } catch (err) {
    console.error(err);
    alert("Failed to load books. Check console for details.");
  } finally {
    setLoading(false);
  }
}

function getBookFormPayload() {
  const bookId = Number(document.getElementById("bookId").value);
  const title = document.getElementById("title").value.trim();
  const author = document.getElementById("author").value.trim();
  const price = Number(document.getElementById("price").value);
  const category = document.getElementById("category").value.trim();

  return {
    bookId,
    title,
    author,
    price,
    category
  };
}

addBookForm.addEventListener("submit", async (e) => {
  e.preventDefault();

  // Basic HTML5 validation
  if (!addBookForm.checkValidity()) {
    addBookForm.classList.add("was-validated");
    return;
  }

  const payload = getBookFormPayload();

  try {
    const resp = await fetch(`${API_BASE_URL}/api/books`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(payload)
    });

    if (!resp.ok) {
      const text = await resp.text().catch(() => "");
      throw new Error(`POST /api/books failed: ${resp.status} ${text}`.trim());
    }

    // Close modal + refresh table
    addBookForm.reset();
    addBookForm.classList.remove("was-validated");
    addBookModal.hide();
    await fetchBooks();
  } catch (err) {
    console.error(err);
    alert("Failed to add book. Check console for details.");
  }
});

btnRefresh?.addEventListener("click", () => {
  fetchBooks();
});

// Initial load
fetchBooks();

