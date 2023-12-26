To Configure the app create file `app.properties` in folder `src/main/resources`. 

Example `app.properties`:
````
# App Types
app_type=STANDALONE
#app_type=STANDALONE_WITH_MIDDLEWARE
#app_type=DISTRIBUTED

# Component Type (set only if App Type is DISTRIBUTED)
#component_type=VIEW
#component_type=CONTROLLER
#component_type=MATCH_MANAGER
#component_type=GAME_LOGIC

# Port number for App (if DISTRIBUTED)
#port=20200

# If AppType is set to STANDALONE_WITH_MIDDLEWARE configure like this:
# view_port= ...
# controller_port= ...

# NameService Info
#nameservice_ip = ...
#nameservice_port= ...
````

