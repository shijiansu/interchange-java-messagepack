Streaming parsers typically come in two versions:

- Pull parsers and push parsers. A pull parser is a parser where the code using it pulls the tokens out of the parser when the code is ready to handle the next token.
- A push parser parses through the JSON tokens and pushes them into an event handler.

The GSON JsonReader is a **pull parser**.
