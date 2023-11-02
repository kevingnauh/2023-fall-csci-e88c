def file_char_count(file_name):
    try:
        with open(file_name, 'r') as file:
            text = file.read()
            return len(text)
    except FileNotFoundError:
        return 0
    except Exception as e:
        print(f"An error occurred: {e}")
        return 0

# Example usage:
file_name = r'D:\Users\Documents\School\CSCI E-88c\_code\2023-fall-csci-e88c\src\test\resources\data\dirty-retail-data-sample.csv'  # Replace with your file's path
character_count = file_char_count(file_name)
print(f"Character count: {character_count}")