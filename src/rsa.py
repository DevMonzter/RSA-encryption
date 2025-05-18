import random
from Crypto.Util import number

def main():
    # Generates pseudo-random exponent, between 64 and 256, to be used in generation of p and q values
    low = 64
    high = 256
    bit_value = random.randint(low, high)
    bit_value2 = random.randint(low, high)

    # Generates probable prime numbers p and q
    p = number.getPrime(bit_value)
    q = number.getPrime(bit_value2)

    # Generates modulus value
    n = p * q

    # Euler's totient function
    m = (p - 1) * (q - 1)

    # Uses standard public key value
    e = 65537

    # Checks that m and e are coprime
    if m % e == 0:
        raise ArithmeticError("e and m are not coprime")

    # Generates private key value
    d = pow(e, -1, m)

    # Generates pseudo-random number to be used as plaintext with a value < n
    plaintext_number = random.randint(1, n - 1)

    # Encrypts the plaintext
    encrypted = pow(plaintext_number, e, n)

    # Decrypts the ciphertext
    decrypted = pow(encrypted, d, n)

    # Prints the RSA values
    print("RSA Algorithm Values")
    print(f"p: {p}")
    print(f"q: {q}")
    print(f"n: {n}")
    print(f"m: {m}")
    print(f"e: {e}")
    print(f"d: {d}")
    print(f"\nPublic Key: ({e}, {n})")
    print(f"Private Key: ({d}, {n})")
    print(f"\nPlaintext Value: {plaintext_number}")
    print(f"Encryption Value: {encrypted}")
    print(f"Decryption Value: {decrypted}")

if __name__ == "__main__":
    main()