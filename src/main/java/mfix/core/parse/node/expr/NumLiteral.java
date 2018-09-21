/**
 * Copyright (C) SEI, PKU, PRC. - All Rights Reserved.
 * Unauthorized copying of this file via any medium is
 * strictly prohibited Proprietary and Confidential.
 * Written by Jiajun Jiang<jiajun.jiang@pku.edu.cn>.
 */
package mfix.core.parse.node.expr;

import mfix.core.comp.Modification;
import mfix.core.parse.match.metric.FVector;
import mfix.core.parse.node.Node;
import org.eclipse.jdt.core.dom.ASTNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: Jiajun
 * @date: 2018/9/21
 */
public class NumLiteral extends Expr {

	private String _token = null;
	
	/**
	 * Null literal node.
	 */
	public NumLiteral(int startLine, int endLine, ASTNode node) {
		super(startLine, endLine, node);
		_nodeType = TYPE.NUMBER;
	}
	
	public void setValue(String token){
		_token = token;
	}
	
	public void setBinding(Node node) {
		_binding = node;
	}
	
	@Override
	public StringBuffer toSrcString() {
		return new StringBuffer(_token);
	}
	
	@Override
	protected void tokenize() {
		_tokens = new LinkedList<>();
		_tokens.add(_token);
	}
	
	@Override
	public boolean compare(Node other) {
		boolean match = false;
		if(other instanceof NumLiteral) {
			NumLiteral numLiteral = (NumLiteral) other;
			match = _token.equals(numLiteral._token);
		}
		return match; 
	}
	
	@Override
	public void deepMatch(Node other) {
		_tarNode = other;
		if(!(other instanceof NumLiteral)) {
			_matchNodeType = false;
		}
	}
	
	@Override
	public boolean matchSketch(Node sketch) {
		boolean match = false;
		if(sketch instanceof NumLiteral) {
			match = true;
			NumLiteral numLiteral = (NumLiteral) sketch;
			numLiteral._binding = this;
			_binding = numLiteral;
		}
		return match;
	}
	
	@Override
	public StringBuffer applyChange(Map<String, String> exprMap, Set<String> allUsableVars) {
		return toSrcString();
	}
	
	@Override
	public StringBuffer replace(Map<String,String> exprMap, Set<String> allUsableVars) {
		String result = exprMap.get(toSrcString().toString());
		if(result != null) {
			return new StringBuffer(result);
		}
		return toSrcString();
	}
	
	@Override
	public boolean bindingSketch(Node sketch) {
		_binding = sketch;
		sketch.setBinding(this);
		return this.matchSketch(sketch);
	}
	
	@Override
	public Map<String, Set<Node>> getKeywords() {
		if(_keywords == null) {
			_keywords = new HashMap<>(0);
		}
		return _keywords;
	}
	
	@Override
	public List<Node> getAllChildren() {
		return new ArrayList<>(0);
	}
	
	@Override
	public StringBuffer printMatchSketch() {
		return new StringBuffer("ERROR");
	}
	
	@Override
	public List<Modification> extractModifications() {
		return new LinkedList<>();
	}
	
	@Override
	public void resetAllNodeTypeMatch() {
		_matchNodeType = false;
	}

	@Override
	public void setAllNodeTypeMatch() {
		_matchNodeType = true;
	}
	
	@Override
	public void computeFeatureVector() {
		_fVector = new FVector();
		_fVector.inc(FVector.INDEX_LITERAL);
	}

}
