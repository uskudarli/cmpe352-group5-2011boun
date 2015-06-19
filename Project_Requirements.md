### REQUIREMENTS Version 1.1 ###

**1.FUNCTIONAL REQUIREMENTS:**

**1.1 SCHEMATIZING**

**1.1.1** Schematizer's input format will be input by the  user through a GUI.

**1.1.1.1** The user will create map by drag and drop operations.

**1.1.2** Schematizer should give the opportunity to choose the angle multiple among these values: 22,5 - 45 - 90 - 180.

**1.1.3** Schematizer should provide these functionalities:

**1.1.3.1** To save output maps to the DBMS via the save button.

**1.1.3.2** To revert to the last saved state of the map.

**1.1.3.3** To edit the previously saved maps of the user.

**1.1.4** Schematizer should enable the user to use the background of her/his choice.

**1.2 MAP INTERACTION**

**1.2.1** Schematizer should be able to create a schematized map of a given scale map.

**1.2.1.1** The Schematizer should be able to schematize maps with multiple paths.

**1.2.1.2** The Schematizer should be able to schematize maps which has multiple routes indicated with different colors.

**1.2.1.3** The Schematizer should provide advanced users to preserve distances in their map.

**1.2.1.4** The Schematizer should provide advanced users to preserve east-west alignment in their map.

**1.2.1.5** The Schematizer should provide advanced users to preserve north-south alignment in their map.

**1.2.2** Schematizer should retain relative geographical direction between points as in the original input map.

**1.2.2.1** Schematizer may modify relative positions of some points.

**1.2.2.2** Schematizer should retain east-west alignment in the map if an advanced user selects it.

**1.2.2.3** Schematizer should retain north-south alignment in the map if an advanced user selects it.

**1.2.2.4** Schematizer should retain distance preservation in the map if an advanced user selects it.

**1.3 USER**

**1.3.1** Schematizer should have different USER ROLES such as ADVANCED USER or SIMPLE USER.

**1.3.1.1** Schematizer should provide the opportunity to select the user role.

**1.3.1.2** Advanced user shall give the configuration file as an input whereas simple user shall use the default configuration file.

**1.3.2** Previously created maps by the user shall be kept in the database.

**1.3.3** The user will be able to edit the map by setting points on the canvas by drag and drop operations.

**1.3.4** The user will be able to search for public maps within the database of the Schematizer.

**1.4 ERROR HANDLING**

**1.4.1** The Schematizer should be designed to limit user driven errors.

**1.4.2** The Schematizer should provide information for user to schematize his/her map.

**1.5 OTHER TOOLS ADAPTATION**

**1.5.1** Schematizer should  give the output in png format.

**2.NONFUNCTIONAL REQUIREMENTS:**

**2.1 Product Requirements**

**2.1.1 Usability**

**2.1.1.1** The Schematizer will have a graphical user interface.

**2.1.1.2** The Schematizer will be used with an Internet connection.

**2.1.1.3** There will be a creation wizard for the schematizing process.

**2.1.2 Efficiency Requirements**

**2.1.2.1**  Schematizer shall not use exceed 128MB of local memory.

**2.1.2.3**  The database system should be recovered from a possible breakdown.

**2.1.3 Dependability**

**2.1.3.1**  Schematizer shall run on platforms with JRE7, internet connection and Google Chrome, Mozilla Firefox, Opera or Internet Explorer installed.

**2.1.4 Security/Privacy**

**2.1.4.1**  Schematizer will a have user system such that every user has a login information and the schematic outputs of users will be stored in the database.

**2.1.4.1.1** Users only have access to their own maps and public maps, a user's maps will not be visible to others if it is a private map.

**2.1.5 Safety**

In unexpected termination of schematizer, database will be able to rollback to its savepoint.

**2.1.6 Availability**

Schematizer will be availible on the web.

**2.2 Organizational Requirements**

**2.3 External Requirements**

**2.3.1** We do not store personal informations of the users except login information(username, password). Login information will not be shared with any commercial or non-commercial actors.
