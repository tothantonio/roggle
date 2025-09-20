file_path = "D:\\toth\\roggle\\src\\boards\\dict.txt"

with open(file_path, "r", encoding="utf-8") as f:
    words = f.readlines()

upper_words = [word.upper() for word in words]

with open(file_path, "w", encoding="utf-8") as fout:
    fout.writelines(upper_words)