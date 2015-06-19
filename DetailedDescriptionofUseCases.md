# Detailed Description of Use Cases #
## Use Case Diagram -1: ##
  * Actors:	User(who wants to create schematized map),The authentication system
  * Description:	User wants to log in to the system. The system authenticates the users according to the user id/password records in the database.
  * Data:	The user id and the password
  * Response:	If the data that the user entered is correct then the system lets the user enters and the main window of the program is shown, but if the id/password is not correct then the system gives a warning message.



## Use Case Diagram -2: ##
  * Actors:	User
  * Description:	In the main window there are following options:
  1. Create a schematic map
  1. Search a map
  1. Edit a map
  1. View a map
  1. Share a map
User is supposed to select an option.
  * Data:	If the user wants to do one of above functions then s/he is supposed to select an input file(from his old records or just browsing from the computer)
  * Response:	If the gathering of the input file operation is completed successfully then the user is followed by the next configuration page, but if the operation is not completed successfully then a warning message will be shown.


## Use Case Diagram -3: ##
  * Actors:	User
  * Description:	The user can select a configuration file or create a new one.
  * Data:	If the user wants to use a file which is already exists then data is the configuration file itself,
If the user wants to create a new configuration file then the data consists of:
  1. preserve North-South alignment
  1. preserve East-West alignment
  1. multiple paths input input file
  1. use color coding
  1. parsing method
  1. choose angle multiple for branching
  * Response:	If the schematizing process is completed successfully then the output map will be shown and the user will have the opportunity to save it as an image.