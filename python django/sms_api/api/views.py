from django.shortcuts import render
from django.http import HttpResponse
from django.http import HttpResponseNotFound
from Crypto.Cipher import AES
import base64
import json

key = 'aesEncryptionKey'.encode('UTF-8')
iv = 'encryptionIntVec'.encode('UTF-8')

# Create your views here.

def home(request):
    import requests
    import random
    from bs4 import BeautifulSoup
    try:
        number=request.GET['number']
        chk=request.GET['chk']
        amount=request.GET.get('amount','0') 
        a,b,c,d,e,f=random.randint(0,9),random.randint(0,9),random.randint(0,9),random.randint(0,9),random.randint(0,9),random.randint(0,9)
        if(chk=="a"):
            MessageBody = 'Otp: '+str(a)+str(b)+str(c)+str(d)+str(e)+str(f)
        elif(chk=="b"):
            MessageBody = str(amount) +" has been deducted from your account for shopping."
        elif(chk=="c"):
            MessageBody = "Wrong OTP entered for transaction using your account"
        api_key="C6CE9AAF-D9EC-3132-2397-7F065BBC42A5"
        api_username="magneto"
        url="https://api-mapper.clicksend.com/http/v2/send.php?method=http&username="+api_username+"&key="+api_key+"&to="+number+"&from=SecureBank"+"&message="+MessageBody
        xy=requests.get(url,verify=False)
        raw = (str(a)+str(b)+str(c)+str(d)+str(e)+str(f)).encode('UTF-8')
        cipher = AES.new(key,AES.MODE_CFB,iv,segment_size=128)
        encrypted = base64.b64encode(cipher.encrypt(raw))
        xx = {"otp":encrypted.decode(),"sms":raw.decode()}
        r = json.dumps(xx)
        return HttpResponse(r)
    except:
        return HttpResponseNotFound("Failed")

def validate(request):
    enc_otp=request.GET['enc_otp']
    otp=request.GET['otp']
    cipher = AES.new(key,AES.MODE_CFB,iv,segment_size=128)
    decrypted = cipher.decrypt(base64.b64decode(enc_otp))
    if(otp==decrypted.decode()):
        x={"otp":"true","sms":"yes"}
        r=json.dumps(x)
        return HttpResponse(r)
    x={"otp":"false","sms":"no"}
    r=json.dumps(x)
    return HttpResponse(r) 
