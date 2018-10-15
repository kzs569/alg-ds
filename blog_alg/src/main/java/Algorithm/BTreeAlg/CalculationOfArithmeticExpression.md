# 算术表达式的计算
Reference:  [算术表达式的计算](https://blog.csdn.net/xoyojank/article/details/1423390)
### 算数表达式的两种表示
1. 中缀表达式
<p>通常书写的算术表达式是由操作数(又叫运算对象或运算量)和运算符以及改变运算次序的圆括号连接而成的式子。操作数可以是常量、变量和函数，同时还可以是表达式。运算符包括单目运算符和双目运算符两类，单目运算符只要求一个操作数，并被放在该操作数的前面，双目运算符要求有两个操作数，并被放在这两个操作数的中间。单目运算符为取正’+’和取负’-’，双目运算符有加’+’,减’-’,乘’*’和除’/’等。为了简便起见，在我们的讨论中只考虑双目运算符。
如对于一个算术表达式2+5*6，乘法运算符’*’的两个操作数是它两边的5和6；对于加法运算符’+’的两个操作数，一个是它前面的2，另一个是它后面的5*6的结果即30。我们把双目运算符出现在两个操作数中间的这种习惯表示叫做算术表达式的中缀表示，这种算术表达式被称为中缀算术表达式或中缀表达式。
中缀表达式的计算比较复杂，它必须遵守以下三条规则：</p>
<p> (1) 先计算括号内，后计算括号外 </p>
<p> (2) 在无括号或同层括号内，先进行乘除运算，后进行加减运算，即乘除运算的优先级高于加减运算的优先级 </p>
<p> (3) 同一优先级运算，从左向右依次进行 </p>
<p> 从这三条规则可以看出，在中缀表达式的计算过程中，既要考虑括号的作用，又要考虑运算符的优先级，还要考虑运算符出现的先后次序。因此，各运算符实际的运算次序往往同它们在表达式中出现的先后次序是不一致的，是不可预测的。当然凭直观判别一个中缀表达式中哪个运算符最先算，哪个次之，……，哪个最后算并不困难，但通过计算机处理就比较困难了，因为计算机只能一个字符一个字符地扫描，要想得到哪一个运算符先算，就必须对整个中缀表达式扫描一遍，一个中缀表达式中有多少个运算符，原则上就得扫描多少遍才能计算完毕，这样就太浪费时间了，显然是不可取的。</p>

2. 后缀表达式（逆波兰式）
<p>波兰科学家卢卡谢维奇(Lukasiewicz)很早就提出了算术表达式的另一种表示，即后缀表示，又称逆波兰式，其定义是把运算符放在两个运算对象的后面。
采用后缀表示的算术表达式被称为后缀算术表达式或后缀表达式。在后缀表达式中，不存在括号，也不存在优先级的差别，
计算过程完全按照运算符出现的先后次序进行，整个计算过程仅需一遍扫描便可完成，显然比中缀表达式的计算要简单得多。
例如，对于后缀表达式12!4!-!5!/，其中’!’字符表示成分之间的空格，因减法运算符在前，除法运算符在后，所以应先做减法，后做除法；
减法的两个操作数是它前面的12和4，其中第一个数12是被减数，第二个数4是减数；除法的两个操作数是它前面的12减4的差(即8)和5，其中8是被除数，5是除数。
中缀算术表达式转换成对应的后缀算术表达式的规则是：把每个运算符都移到它的两个运算对象的后面，然后删除掉所有的括号即可。
例如，对于下列各中缀表达式：
(1) 3/5+6
(2) 16-9*(4+3)
(3) 2*(x+y)/(1-x)
(4) (25+x)*(a*(a+b)+b)
对应的后缀表达式分别为：
(1) 3!5!/!6!+
(2) 16!9!4!3!+!*!-
(3) 2!x!y!+!*!1!x!-!/
(4) 25!x!+!a!a!b!+!*!b!+!*

后缀表达式求值的算法
后缀表达式的求值比较简单，扫描一遍即可完成。
它需要使用一个栈，假定用S表示，其元素类型应为操作数的类型，假定为浮点型float，用此栈存储后缀表达式中的操作数、计算过程中的中间结果以及最后结果。
假定一个后缀算术表达式以字符’@’作为结束符，并且以一个字符串的方式提供。
后缀表达式求值算法的基本思路：
把包含后缀算术表达式的字符串定义为一个输入字符串流对象，每次从中读入一个字符（空格作为数据之间的分隔符，不会被作为字符读入）时，
若它是运算符，则表明它的两个操作数已经在栈S中，
其中栈顶元素为运算符的后一个操作数，栈顶元素的下一个元素为运算符的前一个操作数，
把它们弹出后进行相应运算即可，然后把运算结果再压入栈S中；
否则，读入的字符必为操作数的最高位数字，应把它重新送回输入流中，
然后把下一个数据作为浮点数输入，并把它压入到栈S中。
依次扫描每一个字符（对于浮点数只需扫描它的最高位并一次输入整个浮点数）并进行上述处理，直到遇到结束符’@’为止，
表明后缀表达式计算完毕，最终结果保存在栈中，并且栈中仅存这一个值，把它弹出返回即可。</p>