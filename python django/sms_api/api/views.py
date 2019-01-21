from django.shortcuts import render
from django.http import HttpResponse
from Crypto.Cipher import AES
import base64
import json
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import smtplib
server = smtplib.SMTP('smtp.gmail.com', 587)

server.ehlo()
server.starttls()
#Next, log in to the server
server.login("", "")

key = 'aesEncryptionKey'.encode('UTF-8')
iv = 'encryptionIntVec'.encode('UTF-8')

# Create your views here.

def home(request):
    import requests
    import random
    from bs4 import BeautifulSoup
    number=request.GET['number']
    chk=request.GET['chk']
    email=request.GET['email']
    amount=request.GET.get('amount','0') 
    a,b,c,d,e,f=random.randint(0,9),random.randint(0,9),random.randint(0,9),random.randint(0,9),random.randint(0,9),random.randint(0,9)
    if(chk=="a"):
        MessageBody = 'Otp: '+str(a)+str(b)+str(c)+str(d)+str(e)+str(f)
        #Send the mail
        msg = MIMEMultipart()
        msg['From'] = "hariomagrawal12@gmail.com"
        msg['To'] = email
        msg['Subject'] = "OTP For transaction"
        body = 'Otp: '+str(a)+str(b)+str(c)+str(d)+str(e)+str(f)
        msg.attach(MIMEText(body, 'plain'))
        text = msg.as_string()
        server.sendmail("hariomagrawal12@gmail.com", email, text)
    elif(chk=="b"):
        MessageBody = str(amount) +" has been deducted from your account for shopping."
        #Send the mail
        msg = MIMEMultipart()
        msg['From'] = "hariomagrawal12@gmail.com"
        msg['To'] = email
        msg['Subject'] = "Transaction Detail"
        body = str(amount) +" has been deducted from your account for shopping."
        msg.attach(MIMEText(body, 'plain'))
        text = msg.as_string()
        server.sendmail("hariomagrawal12@gmail.com", email, text)
    elif(chk=="c"):
        MessageBody = "Wrong OTP entered for transaction using your account"
        #Send the mail
        msg = MIMEMultipart()
        msg['From'] = "hariomagrawal12@gmail.com"
        msg['To'] = email
        msg['Subject'] = "Transaction Alert"
        body = "Wrong OTP entered for transaction using your account"
        msg.attach(MIMEText(body, 'plain'))
        text = msg.as_string()
        server.sendmail("hariomagrawal12@gmail.com", email, text)
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

def validate(request):
    enc_otp=request.GET['enc_otp']
    otp=request.GET['otp']
    cipher = AES.new(key,AES.MODE_CFB,iv,segment_size=128)
    decrypted = cipher.decrypt(base64.b64decode(enc_otp))
    if(otp==decrypted.decode()):
        x={"otp":"true"}
        r=json.dumps(x)
        return HttpResponse(r)
    x={"otp":"false"}
    r=json.dumps(x)
    return HttpResponse(r) 
