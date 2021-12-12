import requests
import urllib.request
import zipfile
import os

if os.path.exists('./bobby.zip'):
  os.remove('./bobby.zip')
if os.path.exists('./bobby.jar'):
  os.remove('./bobby.jar')

#requires 'token' file to contain github personal token with public repo access
github_token = open('./token','r').read().rstrip('\n')

artifacts_url = 'https://api.github.com/repos/ahlinist/bobby/actions/artifacts'
artifacts = requests.get(url=artifacts_url)
artifacts_json = artifacts.json()
artifacts_array = artifacts_json['artifacts']
latest_artifact = artifacts_array[0]
zip_url = artifacts_url + '/' + str(latest_artifact['id']) + '/zip'
zip_url_response = requests.get(url=zip_url, headers={'Authorization': 'token ' + github_token})
open('./bobby.zip', 'wb').write(zip_url_response.content)
with zipfile.ZipFile('./bobby.zip', 'r') as zip_ref:
    zip_ref.extractall('.')
os.environ["AMQP_URI"] = "" #put amqp uri here
os.system('java -jar bobby.jar')
