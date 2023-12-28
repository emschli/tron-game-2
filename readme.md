To Configure the app create file `app.properties` in folder `src/main/resources`. 

Example `app.properties`:
````
# App Types
app_type=STANDALONE
#app_type=DISTRIBUTED

# If AppType is set to DISTRIBUTED configure port for each component that should be deployed:
# view_port= ...
# controller_port= ...

# For Testing Purposes:
# Set Component-Factory to Return Mock
# controller_test=true
# game_logic_test=true

````

