#Properties that MAP-Schematizer Architecture Should Support.
# Properties that MAP-Schematizer Architecture Should Support: #
MAP-Schematizer Architecture has a MVC(Model-View-Controller) pattern which seperates presentation and interaction from the system data.
## Performance: ##
  * MAP-Schematizer Architecture has large coponents rather than fine-grain components.
  * MAP-Schematizer Architecture's operations/components have minium communications.
## Security: ##
  * The system is structured into three logical components that interact with each other.
  * The Model component manages the system data and associated operations on that data.(Processing the configuration file properties...etc)
  * The View component defines and manages how the data is presented to the user.(Schematic graph file)
  * The Controller component manages user interaction (e.g., key presses,
mouse clicks, etc.) and passes these interactions to the View and the Model.
## Safety: ##
  * The MAP-Schematizer should provide a protection system that can safely shutdown the system in the event of failure
  * In any crash, database will be able to rollback to its savepoint.
## Availability: ##
  * The MAP-Schematizer should be designed to include redundant components so that it will be possible to replace and update components without stopping the system
  * Schematizer will be accessed from the desktop easily.
  * Additional exporting plugins will be available for the schematizer.
## Maintainability: ##
  * Schematizer will have access to an update system, where any critical change/update to the system will be accomplished.
  * The MAP-Schematizer system should be designed using fine-grain, self-contained components that may readily be changed.