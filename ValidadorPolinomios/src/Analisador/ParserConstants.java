package Analisador;

public interface ParserConstants
{
    int START_SYMBOL = 10;

    int FIRST_NON_TERMINAL    = 10;
    int FIRST_SEMANTIC_ACTION = 19;

    int[][] PARSER_TABLE =
    {
        { -1,  0,  0,  0,  0, -1, -1,  0, -1 },
        {  3, -1, -1,  1,  2, -1, -1, -1,  3 },
        { -1,  4,  4,  4,  4, -1, -1,  4, -1 },
        {  6,  5,  5,  6,  6,  5, -1,  5,  6 },
        {  8,  8,  8,  8,  8,  8,  7,  8,  8 },
        { -1, 11, 11,  9, 10, -1, -1, 11, -1 },
        { -1, 12, 12, 12, 12, -1, -1, 12, -1 },
        { -1, 13, 14, -1, -1, -1, -1, 15, -1 },
        { -1, 17, 17, 17, 17, 16, -1, 17, -1 }
    };

    int[][] PRODUCTIONS = 
    {
        { 49, 12, 50, 11 },
        {  4, 10 },
        {  5, 10 },
        {  0 },
        { 39, 16, 13, 40 },
        { 18, 16, 13 },
        {  0 },
        {  7, 16 },
        {  0 },
        {  4, 21 },
        {  5, 21 },
        {  0 },
        { 20, 15, 17, 14, 27 },
        {  2, 22 },
        {  3, 23 },
        {  8, 10,  9 },
        {  6 },
        {  0 }
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
        "<expressao> inválido",
        "<expressao2> inválido",
        "<termo> inválido",
        "<termo2> inválido",
        "<potencia> inválido",
        "<sinal> inválido",
        "<elemento> inválido",
        "<elemento2> inválido",
        "<multiplicacaoOpcional> inválido"
    };
}
