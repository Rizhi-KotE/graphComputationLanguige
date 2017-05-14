package graph.compiler

package object constants {
  lazy val VERTEX = new TypedStructure("Vertex")
  lazy val EDGE = new TypedStructure("Edge")
  lazy val BOOLEAN = new TypedStructure("Boolean")
  lazy val NUMBER = new TypedStructure("Number")
}

abstract class TypedEntity {
  def matchType(entity: TypedEntity): Boolean

  def entityType: String

}

class TypedStructure(val entityType: String) extends TypedEntity {
  override def matchType(entity: TypedEntity): Boolean =
    entity match {
      case e: TypedStructure => e.entityType == entityType
      case a: AnyType => true
      case _ => false
    }

  override def toString: String = entityType
}

object TypedEntity {
  def unapply(arg: TypedEntity): Option[(String)] = Some(arg.entityType)
}

object TypedStructure {
  def apply(entityType: String): TypedStructure = new TypedStructure(entityType)
}

object TypedFunction {
  def apply(returnType: TypedEntity, params: Seq[TypedEntity]): TypedFunction = new TypedFunction(returnType, params)
}

class TypedFunction(val returnType: TypedEntity, val paramsType: Seq[TypedEntity]) extends TypedEntity {
  override def matchType(entity: TypedEntity): Boolean =
    entity match {
      case s: TypedFunction => {
        if (paramsType.size == s.paramsType.size)
          s.returnType.matchType(returnType) && s.paramsType
            .zip(paramsType).forall(pair => pair._1.matchType(pair._2))
        else false
      }
      case s: AnyType => true
      case _ => false
    }

  override def entityType: String = returnType.entityType

  override def toString: String = s"$returnType(${paramsType.map(_.toString).mkString(",")})"
}

class AnyType extends TypedEntity {
  override def matchType(entity: TypedEntity): Boolean = {
    true
  }

  override def entityType: String = ???
}