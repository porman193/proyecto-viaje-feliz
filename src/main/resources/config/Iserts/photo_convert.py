import os
import base64
import pandas as pd

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
                    sql_file.write(f"INSERT INTO fotografia (id_fotografia,id_propiedad,fotografia) VALUES ({id_fotografia},{id_fotografia},FROM_BASE64('{encoded_data}'));\n")
                id_fotografia += 1
def fix_property():
    # Leer los datos del archivo CSV
    data = pd.read_csv('propiedades.csv')

    # Reemplazar los valores True/False por 1/0
    data.replace({True: 1, False: 0}, inplace=True)

    # Guardar los datos modificados en un nuevo archivo CSV
    data.to_csv('propiedades.csv', index=False)

# Usage
convert_image_to_sql('../img', 'inserts_fotos.sql')
fix_property()
