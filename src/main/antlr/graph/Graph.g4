grammar Graph;

@header {
  package graph;
}

file: form EOF;

form: idtf
    | literal
    | let
    | loop
    | test
    | fn
    | recur
    | action;


recur: '(''recur' '['form*']' ')';

action: '(' idtf form* ')';

let: '(''let' '['binding*']' form+ ')';

loop: '(''loop' '['binding*']' form+ ')';

test: '(''if' form form form')';

fn: '(''fn' param '['param*']' form+ ')';

param: idtf':'type;

type: idtf
    | idtf'<'type'>';

vector: '[' form* ']';

binding: (param form);

idtf: CHARACHTER
    | operator;

literal: vertex
       | edge
       | logical
       | string
       | number
       | vector;

edge: '`('form form form')'
    | '`('form form')';

number: NUMBERS;
string: '"'CHARACHTER'"';

character: CHARACHTER;

vertex: '#'character;

operator: '+'
        | '-'
        | '*'
        | '/'
        | '='
        | '!=';

logical: 'true'|'false';

CHARACHTER: [a-zA-Z]+;
NUMBERS: [0-9]+;
WS  :   [ \t\r\n]+ -> skip ;