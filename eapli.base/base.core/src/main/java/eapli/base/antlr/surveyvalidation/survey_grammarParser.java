// Generated from C:/Users/almei/Desktop/Universidade 2º ano/2º Semestre/Engenharia de Aplicações/lei21_22_s4_2di_03/eapli.base/base.core/src/main/java/eapli/base/antlr/surveyvalidation\survey_grammar.g4 by ANTLR 4.10.1
package eapli.base.antlr.surveyvalidation;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class survey_grammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		HEADER_ID=1, SIGNALS=2, NUMBERS=3, HEADER_TITLE=4, HEADER_TITLE_MIN=5, 
		HEADER_TITLE_MAX=6, WELCOME_HELLO=7, WELCOME_AIMING=8, WELCOME_TIME=9, 
		WELCOME_THANK=10, ID=11, SECTION_DESCRIPTION=12, OBLIGATORINESS=13, OBRIGATORINESS_OTHER=14, 
		OBRIGATORINESS_OPTIONAL=15, REPEATABILITY=16, QUESTION_BODY=17, INSTRUCTION=18, 
		EXTRA_INFO=19, NORMAL=20, CHOICE=21, CHOICE_INPUT=22, OPTIONS=23, ANSWER_CHOICES=24, 
		SCALE=25, SCALE1=26, SCALE_OPTIONS=27, OTHER=28, FINAL_MESSAGE=29, TEXT=30, 
		TEXTWITHSPACES1=31, TEXTWITHSPACES=32, ONE_NEWLINE=33, MANDATORY_NEWLINE=34, 
		PONTUATION=35, WHITESPACE=36;
	public static final int
		RULE_questionary = 0, RULE_header = 1, RULE_title = 2, RULE_welcome_message = 3, 
		RULE_section = 4, RULE_question = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"questionary", "header", "title", "welcome_message", "section", "question"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'Hello,\\n'", null, null, 
			"'Thank you very much for your time and support.'", null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "'You have successfully completed the questionnaire.Thank you for your help.'", 
			null, null, null, "'\\n'", "'\\n\\n'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "HEADER_ID", "SIGNALS", "NUMBERS", "HEADER_TITLE", "HEADER_TITLE_MIN", 
			"HEADER_TITLE_MAX", "WELCOME_HELLO", "WELCOME_AIMING", "WELCOME_TIME", 
			"WELCOME_THANK", "ID", "SECTION_DESCRIPTION", "OBLIGATORINESS", "OBRIGATORINESS_OTHER", 
			"OBRIGATORINESS_OPTIONAL", "REPEATABILITY", "QUESTION_BODY", "INSTRUCTION", 
			"EXTRA_INFO", "NORMAL", "CHOICE", "CHOICE_INPUT", "OPTIONS", "ANSWER_CHOICES", 
			"SCALE", "SCALE1", "SCALE_OPTIONS", "OTHER", "FINAL_MESSAGE", "TEXT", 
			"TEXTWITHSPACES1", "TEXTWITHSPACES", "ONE_NEWLINE", "MANDATORY_NEWLINE", 
			"PONTUATION", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "survey_grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public survey_grammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class QuestionaryContext extends ParserRuleContext {
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public TerminalNode FINAL_MESSAGE() { return getToken(survey_grammarParser.FINAL_MESSAGE, 0); }
		public Welcome_messageContext welcome_message() {
			return getRuleContext(Welcome_messageContext.class,0);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public QuestionaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).enterQuestionary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).exitQuestionary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof survey_grammarVisitor ) return ((survey_grammarVisitor<? extends T>)visitor).visitQuestionary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionaryContext questionary() throws RecognitionException {
		QuestionaryContext _localctx = new QuestionaryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_questionary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			header();
			setState(13);
			title();
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WELCOME_HELLO) {
				{
				setState(14);
				welcome_message();
				}
			}

			setState(18); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(17);
				section();
				}
				}
				setState(20); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(22);
			match(FINAL_MESSAGE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderContext extends ParserRuleContext {
		public TerminalNode HEADER_ID() { return getToken(survey_grammarParser.HEADER_ID, 0); }
		public TerminalNode MANDATORY_NEWLINE() { return getToken(survey_grammarParser.MANDATORY_NEWLINE, 0); }
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof survey_grammarVisitor ) return ((survey_grammarVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(HEADER_ID);
			setState(25);
			match(MANDATORY_NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TitleContext extends ParserRuleContext {
		public TerminalNode HEADER_TITLE() { return getToken(survey_grammarParser.HEADER_TITLE, 0); }
		public TerminalNode MANDATORY_NEWLINE() { return getToken(survey_grammarParser.MANDATORY_NEWLINE, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof survey_grammarVisitor ) return ((survey_grammarVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(HEADER_TITLE);
			setState(28);
			match(MANDATORY_NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Welcome_messageContext extends ParserRuleContext {
		public TerminalNode WELCOME_HELLO() { return getToken(survey_grammarParser.WELCOME_HELLO, 0); }
		public TerminalNode WELCOME_AIMING() { return getToken(survey_grammarParser.WELCOME_AIMING, 0); }
		public TerminalNode WELCOME_TIME() { return getToken(survey_grammarParser.WELCOME_TIME, 0); }
		public TerminalNode WELCOME_THANK() { return getToken(survey_grammarParser.WELCOME_THANK, 0); }
		public TerminalNode MANDATORY_NEWLINE() { return getToken(survey_grammarParser.MANDATORY_NEWLINE, 0); }
		public Welcome_messageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_welcome_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).enterWelcome_message(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).exitWelcome_message(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof survey_grammarVisitor ) return ((survey_grammarVisitor<? extends T>)visitor).visitWelcome_message(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Welcome_messageContext welcome_message() throws RecognitionException {
		Welcome_messageContext _localctx = new Welcome_messageContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_welcome_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(WELCOME_HELLO);
			setState(31);
			match(WELCOME_AIMING);
			setState(32);
			match(WELCOME_TIME);
			setState(33);
			match(WELCOME_THANK);
			setState(34);
			match(MANDATORY_NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SectionContext extends ParserRuleContext {
		public Token OBLIGATORINESS;
		public QuestionContext question;
		public TerminalNode ID() { return getToken(survey_grammarParser.ID, 0); }
		public TerminalNode HEADER_TITLE() { return getToken(survey_grammarParser.HEADER_TITLE, 0); }
		public TerminalNode OBLIGATORINESS() { return getToken(survey_grammarParser.OBLIGATORINESS, 0); }
		public TerminalNode SECTION_DESCRIPTION() { return getToken(survey_grammarParser.SECTION_DESCRIPTION, 0); }
		public TerminalNode REPEATABILITY() { return getToken(survey_grammarParser.REPEATABILITY, 0); }
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof survey_grammarVisitor ) return ((survey_grammarVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_section);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(ID);
			setState(37);
			match(HEADER_TITLE);
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SECTION_DESCRIPTION) {
				{
				setState(38);
				match(SECTION_DESCRIPTION);
				}
			}

			setState(41);
			((SectionContext)_localctx).OBLIGATORINESS = match(OBLIGATORINESS);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REPEATABILITY) {
				{
				setState(42);
				match(REPEATABILITY);
				}
			}

			setState(46); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(45);
					((SectionContext)_localctx).question = question();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(48); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );

			    String question = (((SectionContext)_localctx).question!=null?_input.getText(((SectionContext)_localctx).question.start,((SectionContext)_localctx).question.stop):null);
			    String obrigatory = (((SectionContext)_localctx).OBLIGATORINESS!=null?((SectionContext)_localctx).OBLIGATORINESS.getText():null);
			    String[] questions = question.split("\n\n");
			    if (obrigatory.contains("optional")){
			        for (int i=0; i<questions.length; i++){
			            if (questions[i].contains("mandatory")){
			                throw new IllegalArgumentException("The question " + questions[i] + " must be optional!");
			            }
			        }
			    }

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(survey_grammarParser.ID, 0); }
		public TerminalNode QUESTION_BODY() { return getToken(survey_grammarParser.QUESTION_BODY, 0); }
		public TerminalNode ONE_NEWLINE() { return getToken(survey_grammarParser.ONE_NEWLINE, 0); }
		public TerminalNode NORMAL() { return getToken(survey_grammarParser.NORMAL, 0); }
		public TerminalNode CHOICE() { return getToken(survey_grammarParser.CHOICE, 0); }
		public TerminalNode CHOICE_INPUT() { return getToken(survey_grammarParser.CHOICE_INPUT, 0); }
		public TerminalNode OPTIONS() { return getToken(survey_grammarParser.OPTIONS, 0); }
		public TerminalNode INSTRUCTION() { return getToken(survey_grammarParser.INSTRUCTION, 0); }
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof survey_grammarListener ) ((survey_grammarListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof survey_grammarVisitor ) return ((survey_grammarVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(ID);
			setState(53);
			match(QUESTION_BODY);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INSTRUCTION) {
				{
				setState(54);
				match(INSTRUCTION);
				}
			}

			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				}
				break;
			case 2:
				{
				setState(58);
				match(NORMAL);
				}
				break;
			case 3:
				{
				setState(59);
				match(CHOICE);
				}
				break;
			case 4:
				{
				setState(60);
				match(CHOICE_INPUT);
				}
				break;
			case 5:
				{
				setState(61);
				match(OPTIONS);
				}
				break;
			case 6:
				{
				}
				break;
			}
			setState(65);
			match(ONE_NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001$D\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005"+
		"\u0007\u0005\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u0010\b\u0000"+
		"\u0001\u0000\u0004\u0000\u0013\b\u0000\u000b\u0000\f\u0000\u0014\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004(\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004,\b\u0004\u0001\u0004\u0004"+
		"\u0004/\b\u0004\u000b\u0004\f\u00040\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u00058\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005@\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0000\u0000\u0006\u0000\u0002\u0004"+
		"\u0006\b\n\u0000\u0000H\u0000\f\u0001\u0000\u0000\u0000\u0002\u0018\u0001"+
		"\u0000\u0000\u0000\u0004\u001b\u0001\u0000\u0000\u0000\u0006\u001e\u0001"+
		"\u0000\u0000\u0000\b$\u0001\u0000\u0000\u0000\n4\u0001\u0000\u0000\u0000"+
		"\f\r\u0003\u0002\u0001\u0000\r\u000f\u0003\u0004\u0002\u0000\u000e\u0010"+
		"\u0003\u0006\u0003\u0000\u000f\u000e\u0001\u0000\u0000\u0000\u000f\u0010"+
		"\u0001\u0000\u0000\u0000\u0010\u0012\u0001\u0000\u0000\u0000\u0011\u0013"+
		"\u0003\b\u0004\u0000\u0012\u0011\u0001\u0000\u0000\u0000\u0013\u0014\u0001"+
		"\u0000\u0000\u0000\u0014\u0012\u0001\u0000\u0000\u0000\u0014\u0015\u0001"+
		"\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016\u0017\u0005"+
		"\u001d\u0000\u0000\u0017\u0001\u0001\u0000\u0000\u0000\u0018\u0019\u0005"+
		"\u0001\u0000\u0000\u0019\u001a\u0005\"\u0000\u0000\u001a\u0003\u0001\u0000"+
		"\u0000\u0000\u001b\u001c\u0005\u0004\u0000\u0000\u001c\u001d\u0005\"\u0000"+
		"\u0000\u001d\u0005\u0001\u0000\u0000\u0000\u001e\u001f\u0005\u0007\u0000"+
		"\u0000\u001f \u0005\b\u0000\u0000 !\u0005\t\u0000\u0000!\"\u0005\n\u0000"+
		"\u0000\"#\u0005\"\u0000\u0000#\u0007\u0001\u0000\u0000\u0000$%\u0005\u000b"+
		"\u0000\u0000%\'\u0005\u0004\u0000\u0000&(\u0005\f\u0000\u0000\'&\u0001"+
		"\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000"+
		")+\u0005\r\u0000\u0000*,\u0005\u0010\u0000\u0000+*\u0001\u0000\u0000\u0000"+
		"+,\u0001\u0000\u0000\u0000,.\u0001\u0000\u0000\u0000-/\u0003\n\u0005\u0000"+
		".-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u00000.\u0001\u0000\u0000"+
		"\u000001\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000023\u0006\u0004"+
		"\uffff\uffff\u00003\t\u0001\u0000\u0000\u000045\u0005\u000b\u0000\u0000"+
		"57\u0005\u0011\u0000\u000068\u0005\u0012\u0000\u000076\u0001\u0000\u0000"+
		"\u000078\u0001\u0000\u0000\u00008?\u0001\u0000\u0000\u00009@\u0001\u0000"+
		"\u0000\u0000:@\u0005\u0014\u0000\u0000;@\u0005\u0015\u0000\u0000<@\u0005"+
		"\u0016\u0000\u0000=@\u0005\u0017\u0000\u0000>@\u0001\u0000\u0000\u0000"+
		"?9\u0001\u0000\u0000\u0000?:\u0001\u0000\u0000\u0000?;\u0001\u0000\u0000"+
		"\u0000?<\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?>\u0001\u0000"+
		"\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0005!\u0000\u0000B\u000b\u0001"+
		"\u0000\u0000\u0000\u0007\u000f\u0014\'+07?";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}