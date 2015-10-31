package Analisador;

public interface ParserConstants
{
    int START_SYMBOL = 10;

    int FIRST_NON_TERMINAL    = 10;
    int FIRST_SEMANTIC_ACTION = 18;

    int[][] PARSER_TABLE =
    {
        { -1,  0,  0,  0,  0, -1, -1,  0, -1 },
        {  3, -1, -1,  1,  2, -1, -1, -1,  3 },
        { -1,  4,  4,  4,  4, -1, -1,  4, -1 },
        {  7,  6,  6,  6,  6,  5, -1,  6,  7 },
        {  9,  9,  9,  9,  9,  9,  8,  9,  9 },
        { -1, 12, 12, 10, 11, -1, -1, 12, -1 },
        { -1, 13, 13, 13, 13, -1, -1, 13, -1 },
        { -1, 14, 15, -1, -1, -1, -1, 16, -1 }
    };

    int[][] PRODUCTIONS = 
    {
        { 12, 11 },
        {  4, 10 },
        {  5, 10 },
        {  0 },
        { 16, 13 },
        {  6, 12 },
        { 12 },
        {  0 },
        {  7, 17 },
        {  0 },
        {  4 },
        {  5 },
        {  0 },
        { 15, 19, 17, 14, 22 },
        {  2, 20 },
        {  3, 21 },
        {  8, 10,  9 }
    };

    String[] PARSER_ERROR =
    {
        "",
        "Era esperado fim de programa",
        "Era esperado numero",
        "Era esperado variavel",
        "Era esperado \"+\"",
        "Era esperado \"-\"",
        "Era esperado \"*\"",
        "Era esperado \"^\"",
        "Era esperado \"(\"",
        "Era esperado \")\"",
        "<expressao> inv�lido",
        "<expressao2> inv�lido",
        "<termo> inv�lido",
        "<termo2> inv�lido",
        "<potencia> inv�lido",
        "<sinal> inv�lido",
        "<elemento> inv�lido",
        "<elemento2> inv�lido"
    };
}
