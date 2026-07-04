import urllib.request
import json

url = "https://generativelanguage.googleapis.com/v1beta/models?key=YOUR_API_KEY_HERE"

req = urllib.request.Request(url)
try:
    with urllib.request.urlopen(req) as response:
        print(response.read().decode())
except urllib.error.HTTPError as e:
    print(f"HTTPError: {e.code}")
    print(e.read().decode())
