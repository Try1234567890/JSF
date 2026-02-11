## Phases ¬

- #### ANALYSIS : Scans raw text to identify components, transforms them into logical units, and validates their syntax.
- #### EVALUATION : Executes the logic associated with the components and processes the data.
- #### SYNTHESIS : Injects the final resolved output back into the original raw text.

| Phase         | Component    | Responsibility                                                                                              |
|:--------------|:-------------|:------------------------------------------------------------------------------------------------------------|
| 1. ANALYSIS   | Scanner      | Scans the raw text character by character to identify placeholder boundaries (e.g., locating `${` and `}`). |
|               | Lexer        | Categorizes identified characters into logical units called Tokens (e.g., Tags, Params, Conditions).        |
|               | Parser       | Processes Tokens to verify syntax correctness and maps data into a functional execution object.             |
| 2. EVALUATION | Evaluator    | Executes the logic behind the components (e.g., fetching data from a DB, API, or system variables).         |
| 3. SYNTHESIS  | Interpolator | Handles the "injection" of resolved and cleaned values back into the original static text "skeleton".       |

## System Architecture:

- **Placeholder**: The complete unit of code formed by combining Tag, Functions and Conditions. It represents the entire
  logic required to produce a specific output.
- **Component**: A generic term for the building blocks of an expression. Both Placeholders and Functions are
  categorized as components.
- **Phase**: A specific stage in the processing lifecycle. It describes the "journey" a Component passes through before
  being evaluated and replaced.
- **Tag**: The primary component responsible for generating the initial data. It acts as the core element
  eventually substituted by the final output.
- **Condition**: The condition makes possible show final result only if some requirement are respected,
  otherwise the placeholder will be simply removed.
- **Function**: A component designed to intercept and modify the Tag's output. It applies additional
  transformations, filters, or logic to the data.

## Default Tags:

- `read_file`:
    - *Definition*: Reads and returns the content of a specified file. The system implements an internal cache
      (FileHistory) to optimize performance, updating only if the file size changes.
    - *Params*:
        - "file" or "path":
            - **Definition**: The path to the file to be read;
            - **Valid Options**: A valid file path string.
            - **Default**: An exception is thrown if not specified.

- `system_env`:
    - *Definition*: Retrieves the value of a system property (JVM properties).
    - *Params*:
        - "key":
            - **Definition**: The name of the system property key to retrieve;
            - **Valid Options**: A string (e.g., "os.name", "user.home").
            - **Default**: An exception is thrown if not specified.

- `send_message`:
    - *Definition*: Routes a message to a customizable output stream.
    - *Params*:
        - "message":
            - **Definition**: The content to be transmitted;
            - **Valid Options**: A string.
            - **Default**: "" (empty string).
        - "out":
            - **Definition**: The destination for the message;
            - **Valid Options**:
                - "SOUT": Standard system output;
                - "SERR": Standard system error;
                - "FILE:{FILE_PATH}": Appends the message to a specific file;
                - "URL:{OUT_URL}": Sends a POST request to the provided URL.
            - **Default**: SOUT.
        - "prefix":
            - **Definition**: A string prepended to the message;
            - **Valid Options**: A string.
            - **Default**: "" (empty string).

## Default Conditions:

- `if_time`:
    - *Definition*: Compares a provided time string with the current system time.
    - *Params*:
        - "time":
            - **Definition**: The time value to compare against the current time;
            - **Valid Options**: A string formatted according to the "format" parameter.
            - **Default**: A NullPointerException is thrown if not specified.
        - "format":
            - **Definition**: The pattern used to parse the "time" parameter;
            - **Valid Options**: A valid Java DateTimeFormatter pattern.
            - **Default**: "HH:mm".
        - "comparator":
            - **Definition**: The logical operator used for the comparison;
            - **Valid Options**:
                - *MAJOR* (">" or "major")
                - *MAJOR_OR_EQUALS* (">=" or "major_or_equals")
                - *MINOR* ("<" or "minor")
                - *MINOR_OR_EQUALS* ("<=" or "minor_or_equals")
                - *EQUALS* ("==" or "equals")
                - *NOT_EQUALS* ("!=" or "not_equals")
            - **Default**: EQUALS.

- `if_date`:
    - *Definition*: Compares a provided date string with the current system date.
    - *Params*:
        - "date":
            - **Definition**: The date value to compare against the current date;
            - **Valid Options**: A string formatted according to the "format" parameter.
            - **Default**: A NullPointerException is thrown if not specified.
        - "format":
            - **Definition**: The pattern used to parse the "date" parameter;
            - **Valid Options**: A valid Java DateTimeFormatter pattern.
            - **Default**: "dd.MM.yyyy".
        - "comparator":
            - **Definition**: The logical operator used for the comparison;
            - **Valid Options**:
                - *MAJOR* (">" or "major")
                - *MAJOR_OR_EQUALS* (">=" or "major_or_equals")
                - *MINOR* ("<" or "minor")
                - *MINOR_OR_EQUALS* ("<=" or "minor_or_equals")
                - *EQUALS* ("==" or "equals")
                - *NOT_EQUALS* ("!=" or "not_equals")
            - **Default**: EQUALS.

## Default Functions:

- `to_lower_case`:
    - *Definition*: Transforms the input text (or a specific range of it) to lower case.
    - *Params*:
        - "from":
            - **Definition**: The starting index (inclusive) from which to begin the transformation;
            - **Valid Options**: Any integer (Number).
            - **Default**: 0
        - "to":
            - **Definition**: The ending index (exclusive) at which to stop the transformation;
            - **Valid Options**: Any integer (Number).
            - **Default**: The total length of the input text.

- `to_upper_case`:
    - *Definition*: Transforms the input text (or a specific range of it) to upper case.
    - *Params*:
        - "from":
            - **Definition**: The starting index (inclusive) from which to begin the transformation;
            - **Valid Options**: Any integer (Number).
            - **Default**: 0
        - "to":
            - **Definition**: The ending index (exclusive) at which to stop the transformation;
            - **Valid Options**: Any integer (Number).
            - **Default**: The total length of the input text.

- `index_of`:
    - *Definition*: Returns the index within the input text of the first occurrence of the specified substring.
    - *Params*:
        - "text":
            - **Definition**: The substring to search for;
            - **Valid Options**: A string.
            - **Default**: An exception is thrown if not specified.

- `replace`:
    - *Definition*: Replaces all occurrences of a specified target string with a new replacement string.
    - *Params*:
        - "old" or "from" or "before" or "old_text" or "OT":
            - **Definition**: The sequence of characters to be replaced;
            - **Valid Options**: A string.
            - **Default**: An exception is thrown if not specified.
        - "new" or "to" or "after" or "new_text" or "NT":
            - **Definition**: The sequence of characters to replace the target with;
            - **Valid Options**: A string.
            - **Default**: An exception is thrown if not specified.

- `split`:
    - *Definition*: Splits the input text into an array based on a regex-compatible string.
    - *Params*:
        - "split":
            - **Definition**: The character or regex used to split the text;
            - **Valid Options**: A string.
            - **Default**: An exception is thrown if not specified.
        - "delimiter":
            - **Definition**: If provided, joins the split parts back together using this string;
            - **Valid Options**: A string.
            - **Default**: If omitted, the output returns the array in standard string format (e.g., [part1, part2]).

- `sub_string`:
    - *Definition*: Extracts a specific portion of the input text between two indices.
    - *Params*:
        - "from" or "start":
            - **Definition**: The starting index (inclusive);
            - **Valid Options**: Any integer (Number).
            - **Default**: 0
        - "to" or "end":
            - **Definition**: The ending index (exclusive);
            - **Valid Options**: Any integer (Number).
            - **Default**: The total length of the input text.