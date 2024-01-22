## **Please note: The Usage of the Translation-Ai can be found in the branch `translate_ai_v0.1`**

## Run the App
To Play Alone on a single Node use Example Properties files below and set NameService IP and Ports. Then Start `Main`
To Configure the app create file `app.properties` in folder `src/main/resources`. 

Example `app.properties`:
````
# App Types
#app_type=STANDALONE
app_type=DISTRIBUTED

# If app_type=DISTRIBUTED, choose components that should be deployed:
# Use Comments to disable unwanted components
controller=true
match_manager=true
game_logic=true
view=true

# For Testing Purposes:
# Set Component-Factory to Return Mock
# controller_test=true
# game_logic_test=true

# GameLogic threads
# game_logic_thread_count=2

````

To configure the middleware create file `middleware.properties` in folder `src/main/resources`:
````
# App Types
#app_type=MIDDLEWARE
#app_type=NAME_SERVICE
app_type=BOTH

sync_tcp_port=...
async_tcp_port=...
async_udp_port=...

# Nameservice IP
nameservice_host= ...
nameservice_port= ...

# Number of Receive Threads
tcp_async_threads=2
tcp_sync_threads=2
````
