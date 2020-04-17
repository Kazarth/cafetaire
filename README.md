# cafetaire
Hello
The current status of the project has a functional database and a somewhat functional graphical interface. The two aren't linked though so actual testing has to be done in two parts; junit testing on the database input/output and physically pushing buttons. Most things should be as intended but there are two files that should get some special attention; SupplierPaneRedux will be removed after the sprint as it was temporarily used as a reference; Food and Products are essentially the same class, though Products is used by one of the panes for temporary testing.

# Instructions
We assume the project should work without any big fixes but worst case, follow the instructions below in order to install and set up the dependencies.

# javafx
The system is built using javafx and while its files are included in the dependencies folder changes might need to be made to the project settings in order to make them work.

# junit
We've decided to use JUnit for testing purposes. These files are also included in the dependencies folder but further changes to your IDE might be needed.
To test the system you currently need to comment away row 22 in the Controller class as initialization of Buttons somehow mess up JUnit.

# running the program
Long story short:
clone repo,
install dependencies,
compile

No further changes should be necessary.
