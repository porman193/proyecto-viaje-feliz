import os
import base64

def convert_image_to_sql(folder_path, output_sql_file):
    with open(output_sql_file, 'w') as sql_file:
        sql_file.write("USE viajefeliz;\n")
        id_fotografia = 1
        for filename in os.listdir(folder_path):
            if filename.endswith(".jpg") or filename.endswith(".png"):
                file_path = os.path.join(folder_path, filename)
                with open(file_path, 'rb') as image_file:
                    binary_data = image_file.read()
                    encoded_data = base64.b64encode(binary_data).decode('utf-8')
                    sql_file.write(f"INSERT INTO fotografia (id_fotografia, fotografia) VALUES ({id_fotografia}, FROM_BASE64('{encoded_data}'));\n")
                id_fotografia += 1

# Usage
convert_image_to_sql('../img', 'inserts_fotos.sql')
