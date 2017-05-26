package graph.compiler

import scala.collection.mutable

class ContextMap(var currentLevel: ContextLevel) {
  def findEntityInOneLevel(name: String, entity: AnyStructure): TypedEntity = currentLevel.findEntityCurrentLevel(name, entity)

  def addLevel(): Unit = {
    currentLevel = new ContextLevel(currentLevel)
  }

  def removeLevel(): Unit = {
    currentLevel = currentLevel.parentLevel
  }

  def update(name: String, entity: TypedEntity) = addEntity(name, entity)

  def addEntity(name: String, entity: TypedEntity): Unit = {
    if (currentLevel.findEntity(name, entity) == null)
      currentLevel.addEntity(name, entity)
  }

  def findEntity(name: String, typedEntity: TypedEntity): TypedEntity = {
    currentLevel.findEntity(name, typedEntity) match {
      case null => throw new CannotFindEntityException(s"$name\n$typedEntity")
      case e => e
    }
  }
}

object ContextMap {
  def apply(): ContextMap = {
    val rootLevel = new ContextLevel(null)

    rootLevel.addEntity("graph", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Array<Vertex>"),
        new TypedStructure("Array<Edge>"))))
    rootLevel.addEntity("?", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("Graph"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("?", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("Graph"), new TypedStructure("Edge"))))
    rootLevel.addEntity("?", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("Edge"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("graph", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Array<Vertex>"))))
    rootLevel.addEntity("println", new TypedFunction(new TypedStructure("Unit"),
      Array(new TypedStructure("Graph"))))
    rootLevel.addEntity("println", new TypedFunction(new TypedStructure("Unit"),
      Array(new TypedStructure("Vertex"))))
    rootLevel.addEntity("println", new TypedFunction(new TypedStructure("Unit"),
      Array(new TypedStructure("Edge"))))
    rootLevel.addEntity("println", new TypedFunction(new TypedStructure("Unit"),
      Array(new TypedStructure("Boolean"))))
    rootLevel.addEntity("graph", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Vertex"))))
    rootLevel.addEntity("graph", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Edge"))))
    rootLevel.addEntity("graph", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Array<Edge>"))))
    rootLevel.addEntity("notempty", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("Array<Edge>"))))
    rootLevel.addEntity("notempty", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("Array<Vertex>"))))
    rootLevel.addEntity("head", new TypedFunction(new TypedStructure("Vertex"),
      Array(new TypedStructure("Array<Vertex>"))))
    rootLevel.addEntity("head", new TypedFunction(new TypedStructure("Edge"),
      Array(new TypedStructure("Array<Edge>"))))
    rootLevel.addEntity("tail", new TypedFunction(new TypedStructure("Array<Edge>"),
      Array(new TypedStructure("Array<Edge>"))))
    rootLevel.addEntity("tail", new TypedFunction(new TypedStructure("Array<Vertex>"),
      Array(new TypedStructure("Array<Vertex>"))))
    rootLevel.addEntity("adjacment", new TypedFunction(new TypedStructure("Array<Vertex>"),
      Array(new TypedStructure("Graph"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("+", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Graph"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("+", new TypedFunction(new TypedStructure("Array<Vertex>"),
      Array(new TypedStructure("Array<Vertex>"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("+", new TypedFunction(new TypedStructure("Array<Edge>"),
      Array(new TypedStructure("Array<Edge>"), new TypedStructure("Edge"))))
    rootLevel.addEntity("+", new TypedFunction(new TypedStructure("Array<Graph>"),
      Array(new TypedStructure("Array<Graph>"), new TypedStructure("Graph"))))
    rootLevel.addEntity("+", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Graph"), new TypedStructure("Edge"))))
    rootLevel.addEntity("-", new TypedFunction(new TypedStructure("Array<Vertex>"),
      Array(new TypedStructure("Array<Vertex>"), new TypedStructure("Graph"))))
    rootLevel.addEntity("+", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Graph"), new TypedStructure("Array<Vertex>"), new TypedStructure("Array<Edge>"))))
    rootLevel.addEntity("=", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("Graph"), new TypedStructure("Graph"))))
    rootLevel.addEntity("=", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("Vertex"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("=", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("Edge"), new TypedStructure("Edge"))))
    rootLevel.addEntity("edge", new TypedFunction(new TypedStructure("Edge"),
      Array(new TypedStructure("Vertex"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("edge", new TypedFunction(new TypedStructure("Edge"),
      Array(new TypedStructure("Graph"), new TypedStructure("Vertex"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("vertexes", new TypedFunction(new TypedStructure("Array<Vertex>"),
      Array(new TypedStructure("Graph"))))
    rootLevel.addEntity("edges", new TypedFunction(new TypedStructure("Array<Edge>"),
      Array(new TypedStructure("Graph"))))
    new ContextMap(new ContextLevel(rootLevel))
  }
}

class ContextLevel(var parentLevel: ContextLevel) {
  val table = new mutable.HashMap[String, mutable.Set[TypedEntity]]() with mutable.MultiMap[String, TypedEntity]

  def findEntityCurrentLevel(name: String, entity: AnyStructure): TypedEntity = {
    val result = table.get(name) match {
      case Some(e) => e.result.filter(entity.matchType)
      case None => Set()
    }
    if(result.isEmpty) null
    else result.head
  }

  def findEntity(name: String, typedEntity: TypedEntity): TypedEntity = {
    val result = table.get(name) match {
      case Some(e) => e.result.filter(typedEntity.matchType)
      case None => Set()
    }
    if (result.isEmpty) {
      if (parentLevel != null) parentLevel.findEntity(name, typedEntity)
      else {
        null
      }
    } else {
      if (result.size == 1) {
        result.head
      } else {
        throw new CannotChooseVariableValueException(s"cannot choose between functions with name ${name}\nand definitions ${result.toString()}")
      }
    }
  }

  def addEntity(name: String, entity: TypedEntity): Unit = {
    table.addBinding(name, entity)
  }
}