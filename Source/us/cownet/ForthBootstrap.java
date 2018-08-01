package us.cownet;

public class ForthBootstrap {
	/*
	:P >r
	:P pick
	: dup 1 pick ;
	:P @
	:P rot
	: swap 1 rot ;
	:P +
	:P r>
	:P compileTOS()
	: compile: >r dup @ swap 1 + r> compileTOS() ;

	:P compilerPosition()

	:I while: compilerPosition() ;

	:P (JMP_IF_FALSE)

	:P (NOP)

	:I do compile: (JMP_IF_FALSE) compilerPosition() compile: (NOP) ;

	:P (JMP)

	: compileJumpTo()           // where to jump to ---
		compile: (JMP) compilerPosition() 1 + - compileTOS()
	;

	:P -
	: compileJumpFrom()                // where to jump from to current pos
		dup compilerPosition() swap - swap !
	;

	:I :while   // whilePos exitJmpPos
		swap    // exitJmpPos whilePos
		compileJumpTo()     // jump to while condition
		compileJumpFrom()   // jump from jmp-if-false
	;

	:I if:
		 compile: JMP_IF_FALSE
	;

	:I :if
		compileJumpFrom()   // jump from jmp-if-false
	;

	:I :else:
		compile: (JMP)
		compilerPosition()
		compile: (NOP)
		swap
		compileJumpFrom()
	;

	: repeat:
		compilerPosition()
	;
	: :until
		compile: (JMP_IF_FALSE) compilerPosition() compilerPosition() 1 + - compileTOS();
	;

	:P :c[]
	:P (PUSH_NEXT_INSTRUCTION_WORD_AS_DATA)

	:P c@          // addr -- val
	:P !=
	// return the length of a null terminated string
	: strLength() // addr -- length
		dup
		while: dup c@ != 0 do
			1 +
		:while
		swap -
	;

	: MAX_INPUT_LENGTH 255 ;
	:c[] keyboardBuffer 256 ;
	:P c!          // val addr --
	0 keyboardBuffer c!
	:P accept()    // addr len --
	:P ==
	: readLine()
		repeat:
			keyboardBuffer MAX_INPUT_LENGTH accept()
			keyboardBuffer c@ 0 !=
		:until
	;

	:P drop
	:P :word
	:word token ;
	:P !           // addr val --
	keyboardBuffer token !
	:word tokenPos ;
	token @ tokenPos !
	: @1+! dup @ 1 + swap ! ;
	: getNextChar()                 // -- char
		tokenPos @ c@ dup 0 == if:  // EOL
			readLine()
			keyboardBuffer token !
			keyboardBuffer tokenPos !
		:else:
			tokenPos @1+!
		:if
	;

	: isDelimiter() // char -- bool
		dup 32 == if:
			1
		:else:
			dup 0 == if:
				1
			:else:
				0
			:if
		:if
	;

	: getNextToken
		// skip delimeters
		// collect until delimeter










	: skipDelimiterChar()
		token @ c@ 0 == if:
			readLine()
		:if
	;

	: nextToken()
		token @ c@ 0 == if:
			readTokens()
		:if
		token ((token @) + (token @ strLength())) !
	;

	: clearTib()
		keyboardBuffer0 c!
		token keyboardBuffer!
	;

	:P :char
	:char keepRunningCommandLineInterp ;
	keepRunningCommandLineInterp 1 c!
	:P find()      // strAddr -- word bool 1 or 0
	:P executeTOS()  // addr --
	:P parseNumber()   // str -- value 1 or 0
	:P parseString()
	:P print()     // addr --
	:P println()   // addr --
	:P clearTib()
	: commandLineInterp
		while: keepRunningCommandLineInterp @ do
			token @ find() if:
				nextToken()
				drop
				executeTOS()
			:else:
				token @ parseNumber() if:
					nextToken()
				:else:
					token @ parseString() if:
						nextToken();
					:else:
						token @ print()
						"?" println()
						clearTib()
					:if
				:if
			:if
		:while
	;

	:P beginCompileNewWord()            //  strAddr --
	:P abortCompileNewWord()
	:P endCompileNewWord()
	: compileWord
		beginCompileNewWord()
		nextToken()
		while: token @ c@ ';' != do
			token @ find() if:
				if: //immediate word
					executeTOS();
				:else:
					compileTOS()
				:if
			:else:
				token @ parseNumber() if:
					compile: (PUSH_NEXT_INSTRUCTION_WORD_AS_DATA)
					compileTOS()
				:else:
					token @ parseString() if:
						compile: (PUSH_NEXT_INSTRUCTION_WORD_AS_DATA)
						compileTOS()
					:else:
						// word not a literal or found in dictionary
						token @ print()
						"?" println()
						clearTib()
						abortCompileNewWord()
					:if
				:if
			:if
			nextToken()
		:while
		endCompileNewWord()
	;

	: :
		0
		compileWord();
	;

	: :I
		1
		compileWord()
	;

	:P :w[]
	:P -1
	:P 0
	:P 1
	:P 255
	:P 256
	:P 65535
	:P 65536

		*/
	public void buildMemory(ThreadMemory memory) {

	}
}
