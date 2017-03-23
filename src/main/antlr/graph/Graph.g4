grammar Graph;

@header {
  package graph;
}

file: form EOF;

form: literal
    | action
    | vector;

action: '(' form* ')';

let: '(''let' '['binding*']' form* ')';

loop: '(''loop' '['binding*']' form* ')';

test: '(''if' check then otherwise')';

then: form;

otherwise: form;

check: form;

vector: '[' form* ']';

binding: literal form;

literal: character
       | vertex
       | operator
       | logical;

character: CHARACHTER;

vertex: '#'character;

operator: '+'
        | '-'
        | '*'
        | '/';

logical: 'true'|'false';

CHARACHTER: [a-zA-Z0-9]+;
LEXEM  :   LETTER+ ;
OPERATION: [a-z]+;
LETTER: [a-zA-Z0-9];
WS  :   [ \t\r\n]+ -> skip ;