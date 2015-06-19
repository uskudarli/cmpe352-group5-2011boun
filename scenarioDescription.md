# Scenario Description(Version1.2) #
This page defines the workflow of Schematizer for a simplistic scenario. In this scenario, the user of the schematizer is assummed not to be a power-user. The user is assumed to have an input file(text file). The output will be a vectorial image.

## Details ##
The user starts the program, by double-clicking the icon on the desktop. After the succesful opening of the program window,
first the user clicks on the option "I am an amateur",and then imports the text file using:

File-> Import -> from text file
After clicking "from text file" menu item, the Import Wizard pops up. New window has such format:
  * field for the path of the source text file
  * field for the path of the configuration file
  * Checkbox that says "force for the fixed points"

The input source text file is the file of the map of "Bogazici University".
Then,by clicking the "Next" button, a "Configuration Wizard" pops up as soon as the "Import Wizard" dies. Configuration window has such format:

several checkboxes for:
  * preserve North-South alignment(.pNS)
  * preserve East-West alignment(.pEW)
  * multiple paths input input file(.MuP)
  * use color coding(if .MuP is checked)
several dropdown menus for:
  * choose parsing method
  * choose angle multiple for branching


The user needs to enter the following data:
  * preserve North-South alignment:ON
  * preserve East-West alignment:OFF
  * multiple paths input input file:OFF
  * use color coding:ON
  * parsing method:Real Time
  * choose angle multiple for branching:45


After the configuration is done properly, the user click "finish" button, and configuration wizard dies.

There the schematizer starts to parse the data with the restrictions of the configuration file. While the data is being processed, a small pop-up window shows "Please Wait, it will take several minutes".

After the parsing done, schematized output will be displayed in the main frame of the main program window in the following file format:
> Immediate output: The user can save the schematized map as a vectorial image.


After all is done succesfully(or for any reasons, prematurely), the user can close the program using;

  * close button
  * menu item: file -> exit

# Abnormal Cases #

**1** While the map is saved to the database,if the connection is lost during save process,user logs in again and the map is recovered from the last successful backup.

**2** If the user forgets the password, the new temporary password is sent to its registered mail address.

**3** While the schematizer is running,if it is closed or stops for any reason, when it is restarted, it will continue from the last successful step.

**4** When the user loses the original schematized map,s/he can use the map in the database by simply logging in.

#summary Simple Usage Scenario

# Scenario Description(Version 1.1) #

This page defines the workflow of Schematizer for a simplistic scenario.
In this scenario, the user of the schematizer is assummed not to be a power-user. The user is assumed to have an input file(text file). The output will be a vectorial image.

## Details ##

The user starts the program, by double-clicking the icon on the desktop. After the succesful opening of the program window, the user imports the text file using:

> File-> Import -> from text file

After clicking "from text file" menu item, the Import Wizard pops up. New window has such format:
  * field for the path of the source text file
  * field for the path of the configuration file
  * Checkbox that says "force for the fixed points"

if user doesn't choose a valid configuration file(from his own sources or predefined configuration files from the Schematizer's own configuration set), by clicking the "Next" button, a "Configuration Wizard" pops up as soon as the "Import Wizard" dies. Configuration window has such format:
> several checkboxes for:
  * preserve North-South alignment(.pNS)
  * preserve East-West alignment(.pEW)
  * multiple paths input input file(.MuP)
  * use color coding(if .MuP is checked)
> several dropdown menus for:
  * choose parsing method
  * choose angle multiple for branching

After the configuration is done properly, the user click "finish" button, and configuration wizard dies.

There the schematizer starts to parse the data with the restrictions of the configuration file. While the data is being processed, a small pop-up window shows "Please Wait, it will take several minutes".

After the parsing done, schematized output will be displayed in the main frame of the main program window. There the user will have 3 possibilities:
  1. Immediate output: The user can save the schematized map as a vectorial image.
  1. Export to somewhere: The user can export the vectorial image to another program using related plugin.
  1. Edit mode: For some geographical or any other reasons, The user possibly wants to edit the schematized image; for achieving this goal, the schematizer allows drag&drop to change the places of the vetices of the map. For further professional looking, the user can add a background image to main frame, which can be used as a canvas in editing mode. After editing user can output using techniques mentioned above.

After all is done succesfully(or for any reasons, prematurely), the user can close the program using;
  * close button
  * menu item: file -> exit



@TODO version1 is completed, feel free to add stuff (SS)
# Scenario Description(Version 1.0) #


TODO scenario 2 Sceneraio 2

The user opens the program and selects the option to set points to create a map.

A canvas is created where user can create a map by adding and joining points (centers). The user can label these. The user may also import a background image to use as a reference for drawing the map geographically by using:

> File-> Import -> Background Image

The user clicks the create button.

Configuration window pops up where the user may change the configuration or import from an already defined configuration file. When finished the user clicks "done" button.

The schematizing process starts and the user is informed about the process.

After the process is done a new schematic map is created. The user may edit points if s/he thinks some parts are not proper.
The user can export the map as vectoral images, or other image formats. Also the user can upload it to the database.
The user exits the program by clicking the close button at the top right of window or by choosing File>Exit.