import sha3

# DPG Method
def dpg(salts, password_size, allowed_chars):
    # Seed generation(using sha3 256)
    s = bytearray(sha3.sha3_256(("".join(salts)).encode('utf-8')).digest())
    seed = int.from_bytes(s, byteorder='big')

    # Password generation(using a deterministic random number generator)
    output = ""
    for _ in range (0,password_size):
        seed = (1_103_515_245 * seed + 12345) % 999_999_937
        output += allowed_chars[seed % len(allowed_chars)]

    return output


# Example use
print(dpg(["website.com", "thomas", "", "ex@mple"], 40,
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"))


# Example implementation
salts = []
fields = ["website url", "login", "additional salt", "main password"]
for field in fields:
   salts.append(input("Enter " + field + ": "))
password_size = input("password size: ")
allowed_chars = input("allowed chars: ")
print(dpg(salts, password_size, allowed_chars))