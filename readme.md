To Configure the app create file `app.properties` in folder `src/main/resources`. 

Example `app.properties`:
````
# App Types
app_type=STANDALONE
#app_type=DISTRIBUTED

# If app_type=DISTRIBUTED, choose components that should be deployed:
# controller=true
# match_manager=true

# For Testing Purposes:
# Set Component-Factory to Return Mock
# controller_test=true
# game_logic_test=true

````

