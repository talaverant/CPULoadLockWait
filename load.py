#!/bin/bash

import traceback
import threading
import time

try:
    # python 2
    from urllib2 import urlopen
except ImportError:
    # python 3
    from urllib.request import urlopen


# Simulate http server
def simulate_http_traffic():
    while True:
        try:
            print('Requesting...')
            response = urlopen('http://127.0.0.1:4567/hello', timeout=10)
            result_data = response.read()
            time.sleep(0.5)
            print('OK')
        except:
            traceback.print_exc()
            pass


t = threading.Thread(target=simulate_http_traffic)
t.start()


