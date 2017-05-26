package graph.compiler

class CannotChooseVariableValueException(message: String) extends Exception(message)
class AlreadyDefinedException(message: String) extends Exception(message)
class CannotFindEntityException(message: String) extends Exception(message)

class TypeMismatchException(message: String) extends Exception(message)