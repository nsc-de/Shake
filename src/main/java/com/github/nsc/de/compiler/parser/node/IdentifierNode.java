package com.github.nsc.de.compiler.parser.node;

public class IdentifierNode implements ValuedNode {

    private final ValuedNode parent;
    private final String name;

    public IdentifierNode(ValuedNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public IdentifierNode(String name) {
        this.parent = null;
        this.name = name;
    }

    public ValuedNode getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }
}