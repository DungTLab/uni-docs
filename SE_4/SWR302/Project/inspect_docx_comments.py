import sys
import docx

def main():
    sys.stdout.reconfigure(encoding='utf-8')
    doc = docx.Document("Project/Project replease/SRS.docx")
    
    # In python-docx, comments are in the document's part package
    # We can inspect the parts to see if there is a comments part
    comments_part = None
    for part in doc.part.package.parts:
        if "comments" in part.partname:
            comments_part = part
            print(f"Found comments part: {part.partname}")
            break
            
    if comments_part:
        # Read the XML and parse it
        import xml.etree.ElementTree as ET
        xml_content = comments_part.blob
        root = ET.fromstring(xml_content)
        ns = {'w': 'http://schemas.openxmlformats.org/wordprocessingml/2006/main'}
        for comment in root.findall('.//w:comment', ns):
            author = comment.get('{http://schemas.openxmlformats.org/wordprocessingml/2006/main}author')
            c_id = comment.get('{http://schemas.openxmlformats.org/wordprocessingml/2006/main}id')
            texts = []
            for t in comment.findall('.//w:t', ns):
                if t.text:
                    texts.append(t.text)
            print(f"Comment ID {c_id} by {author}: '{''.join(texts)}'")
    else:
        print("No Microsoft Word comments part found.")

if __name__ == "__main__":
    main()
