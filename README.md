# DateDiffTool
This program outputs the number of dates provided between two dates.

To run this program: 
- Navigate to the src directory.
- Compile the app using command: `javac -cp . com/abhi/rampal/datediff/*.java`
    -   To run the app using command line standard input: 
        - `java -cp . com/abhi/rampal/datediff/Main`
        -   Input the two dates. For example, if I want to find the difference 12 June 2015 and 12 July 2015, I would type: 
    `12 06 2015, 12 07 2015` and press ENTER.
        -   Once you press enter it would output the result for you in the format
    DD MM YYYY, DD MM YYYY, difference
        - You can continue repeating the 2 steps above to get different outputs based on
the input.
        - To terminate and exit the program press Ctrl + C

            # <img src="/example-input.PNG">
     - To run the app using command line by supplying a file name:
        - `java -cp . com/abhi/rampal/datediff/Main ../resources/dates.csv`
            - where dates.csv is the data file.
        - Below is an example run:
            # <img src="/example-input-file.PNG">

