package graph.compiler

import scala.collection.mutable

class ContextMap(var currentLevel: ContextLevel) {
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
      case null => throw new CannotFindEnetityException(s"$name\n$typedEntity")
      case e => e
    }
  }
}

object ContextMap {
  def apply(): ContextMap = {
    val rootLevel = new ContextLevel(null)
    rootLevel.addEntity("graph", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("List<Vertex>"),
        new TypedStructure("List<Edge>"))))
    rootLevel.addEntity("graph", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("List<Vertex>"))))
    rootLevel.addEntity("graph", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("List<Edge>"))))
    rootLevel.addEntity("notempty", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("List<Edge>"))))
    rootLevel.addEntity("notempty", new TypedFunction(new TypedStructure("Boolean"),
      Array(new TypedStructure("List<Vertex>"))))
    rootLevel.addEntity("head", new TypedFunction(new TypedStructure("Vertex"),
      Array(new TypedStructure("List<Vertex>"))))
    rootLevel.addEntity("head", new TypedFunction(new TypedStructure("Edge"),
      Array(new TypedStructure("List<Edge>"))))
    rootLevel.addEntity("tail", new TypedFunction(new TypedStructure("List<Edge>"),
      Array(new TypedStructure("List<Edge>"))))
    rootLevel.addEntity("tail", new TypedFunction(new TypedStructure("List<Vertex>"),
      Array(new TypedStructure("List<Vertex>"))))
    rootLevel.addEntity("adjacment", new TypedFunction(new TypedStructure("List<Vertex>"),
      Array(new TypedStructure("Graph"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("+", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Graph"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("+", new TypedFunction(new TypedStructure("Graph"),
      Array(new TypedStructure("Graph"), new TypedStructure("Edge"))))
    rootLevel.addEntity("edge", new TypedFunction(new TypedStructure("Edge"),
      Array(new TypedStructure("Vertex"), new TypedStructure("Vertex"))))
    rootLevel.addEntity("edge", new TypedFunction(new TypedStructure("Edge"),
      Array(new TypedStructure("Graph"), new TypedStructure("Vertex"), new TypedStructure("Vertex"))))
    new ContextMap(new ContextLevel(rootLevel))
  }
}

class ContextLevel(var parentLevel: ContextLevel) {
  val table = new mutable.HashMap[String, mutable.Set[TypedEntity]]() with mutable.MultiMap[String, TypedEntity]

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
        throw new CannotChooseVariableValueException(s"\n$name\n${result.toString()}")
      }
    }
  }

  def addEntity(name: String, entity: TypedEntity): Unit = {
    table.addBinding(name, entity)
  }
}