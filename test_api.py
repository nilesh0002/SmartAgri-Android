import urllib.request
import json

url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=YOUR_API_KEY_HERE"
headers = {
    "Content-Type": "application/json"
}
data = {
    "contents": [{"parts": [{"text": "Explain how AI works in a few words"}]}]
}

req = urllib.request.Request(url, json.dumps(data).encode('utf-8'), headers)
try:
    with urllib.request.urlopen(req) as response:
        print(response.read().decode())
except urllib.error.HTTPError as e:
    print(e.read().decode())
