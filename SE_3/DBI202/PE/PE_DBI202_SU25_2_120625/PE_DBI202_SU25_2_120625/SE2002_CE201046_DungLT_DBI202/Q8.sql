UPDATE Product
SET product_price = product_price * 1.1
WHERE product_price < (
    SELECT MAX(li.price)
    FROM LineItem li
    WHERE Product.product_id = li.product_id
);