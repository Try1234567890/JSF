## TO-DO ¬

- Functions Feature: A functions is an additional change to the final result; For example:
    - "lower_case": `Makes to lower case the final result;`
    - "upper_case": `Makes to upper case the final result;`
    - "sub_string": `The final result will be the extracted sub string at the provided indexes (from-to);`
    - "index_of": `The final result will be the index of the provided char or string;`
    - "replace": `Replace all provided char, string or regex from the final result.`

## Phases ¬

- #### ANALYSIS : Scans the raw text to identify the valid components, transform it into logic units and validate them.
- #### EVALUATION : Executes the logic behind the components and validate it.
- #### SYNTHESIS : Replace the output in the raw text.

| Phase         | Component    | Responsibility                                                                                                                   |
|:--------------|:-------------|:---------------------------------------------------------------------------------------------------------------------------------|
| 1. ANALYSIS   | Scanner      | Scans the raw text character by character. Identifies the boundaries of placeholders (e.g., finding where ${ starts and } ends). |
|               | Lexer        | Categorizes identified characters into logical units called Tokens (e.g., Tag, Params, Conditions).                              |
|               | Parser       | Processes the Tokens to verify syntax correctness and maps the data into a functional execution object                           |
| 2. EVALUATION | Evaluator    | Executes the logic behind the components (e.g., fetching data from a DB, API, or system variables).                              |
| 3. SYNTHESIS  | Interpolator | Handles the "injection" of resolved and cleaned values back into the original static text "skeleton".                            |

## System Architecture:

- **Expression**: The complete unit of code formed by the combination of a Placeholder and a Function. It represents the
  entire logic required to produce a specific output.
- **Component**: A generic term for the building blocks of an expression. Both Placeholders and Functions are
  categorized as components.
- **Phase**: A specific stage in the processing lifecycle. It describes the "journey" or state a Component passes
  through before being evaluated and replaced by the final result.
- **Placeholder**: The primary component responsible for generating the initial data or content. It acts as the core
  element that is eventually substituted by the final output.
- **Function**: A component designed to intercept and modify the Placeholder's output. Its purpose is to apply
  additional transformations, filters, or logic to the data provided by the tag.

## Default Tags:

- `send_message`:
    - *Definition*: Send a message to a customizable output.
    - *Params*:
        - "message":
            - **Definition**: The message to send;
            - **Valid Options**: A string.
            - **Default**: "" (empty string)
        - "out":
            - **Definition**: Where the message will be sent:
            - **Valid Options**:
                - "SOUT": Default terminal of the process;
                - "SERR": Default terminal of the process as error;
                - "FILE:{FILE_PATH}": To the provided file;
                - "URL:{OUT_URL}": To the provided URL.
            - **Default**: SOUT
        - "prefix":
            - **Definition**: The prefix of the message;
            - **Valid Options**: A string.
            - **Default**: "" (empty string)

## Default Conditions:

- `if_time`:
    - *Definition*: Compare the provided time with the current time.
    - *Params*:
        - "time":
            - **Definition**: The time to compare with the current one;
            - **Valid Options**: A valid time as string, that correspond to the "format" param.
            - **Default**: An exception is thrown.
        - "format":
            - **Definition**: The format that the provided time follow;
            - **Valid Options**: A valid time format as string (
              see: [Java SimpleDateFormat Docs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/text/SimpleDateFormat.html)).
            - **Default**: "HH:mm".
        - "comparator":
            - **Definition**: How the two times are compared;
            - **Valid Options**:
                - *MAJOR*:
                    - **Symbol**: ">"
                    - **Definition**: Checks if the provided time is major that the current one.
                - *MAJOR_OR_EQUALS*:
                    - **Symbol**: ">="
                    - **Definition**: Checks if the provided time is major or equals that the current one.
                - *MINOR*:
                    - **Symbol**: "<"
                    - **Definition**: Checks if the provided time is minor that the current one.
                - *MINOR_OR_EQUALS*:
                    - **Symbol**: "<="
                    - **Definition**: Checks if the provided time is minor or equals that the current one.
                - *EQUALS*:
                    - **Symbol**: "=="
                    - **Definition**: Checks if the provided time is equals that the current one.
                - *NOT_EQUALS*:
                    - **Symbol**: "!="
                    - **Definition**: Checks if the provided time is not equals that the current one.
            - **Default**: EQUALS.
- `if_date`:
    - *Definition*: Compare the provided date with the current date.
    - *Params*:
        - "date":
            - **Definition**: The date to compare with the current one;
            - **Valid Options**: A valid date as string, that correspond to the "format" param.
            - **Default**: An exception is thrown.
        - "format":
            - **Definition**: The format that the provided date follow;
            - **Valid Options**: A valid date format as string (
              see: [Java SimpleDateFormat Docs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/text/SimpleDateFormat.html)).
            - **Default**: "dd.MM.yyyy".
        - "comparator":
            - **Definition**: How the two dates are compared;
            - **Valid Options**:
                - *MAJOR*:
                    - **Symbol**: ">"
                    - **Definition**: Checks if the provided date is major that the current one.
                - *MAJOR_OR_EQUALS*:
                    - **Symbol**: ">="
                    - **Definition**: Checks if the provided date is major or equals that the current one.
                - *MINOR*:
                    - **Symbol**: "<"
                    - **Definition**: Checks if the provided date is minor that the current one.
                - *MINOR_OR_EQUALS*:
                    - **Symbol**: "<="
                    - **Definition**: Checks if the provided date is minor or equals that the current one.
                - *EQUALS*:
                    - **Symbol**: "=="
                    - **Definition**: Checks if the provided date is equals that the current one.
                - *NOT_EQUALS*:
                    - **Symbol**: "!="
                    - **Definition**: Checks if the provided date is not equals that the current one.
            - **Default**: EQUALS.