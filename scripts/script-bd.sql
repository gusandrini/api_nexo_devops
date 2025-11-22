IF OBJECT_ID('dbo.T_NX_PREDICAO', 'U') IS NOT NULL DROP TABLE dbo.T_NX_PREDICAO;
IF OBJECT_ID('dbo.TB_FUNCAO_USUARIO', 'U') IS NOT NULL DROP TABLE dbo.TB_FUNCAO_USUARIO;
IF OBJECT_ID('dbo.TB_NX_DESCRICAO_CLIENTE', 'U') IS NOT NULL DROP TABLE dbo.TB_NX_DESCRICAO_CLIENTE;
IF OBJECT_ID('dbo.TB_NX_CAMPO_ESTUDO', 'U') IS NOT NULL DROP TABLE dbo.TB_NX_CAMPO_ESTUDO;
IF OBJECT_ID('dbo.TB_NX_INFLUENCIA_FAM', 'U') IS NOT NULL DROP TABLE dbo.TB_NX_INFLUENCIA_FAM;
IF OBJECT_ID('dbo.TB_NX_NIVEL_EDUCACAO', 'U') IS NOT NULL DROP TABLE dbo.TB_NX_NIVEL_EDUCACAO;
IF OBJECT_ID('dbo.TB_NX_OCUPACAO', 'U') IS NOT NULL DROP TABLE dbo.TB_NX_OCUPACAO;
IF OBJECT_ID('dbo.TB_NX_FUNCAO', 'U') IS NOT NULL DROP TABLE dbo.TB_NX_FUNCAO;
IF OBJECT_ID('dbo.TB_NX_USUARIO', 'U') IS NOT NULL DROP TABLE dbo.TB_NX_USUARIO;


/* ============================================================
   SEQUENCES
   ============================================================ */

CREATE SEQUENCE SQ_TB_NX_USUARIO           AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_TB_NX_FUNCAO            AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_TB_FUNCAO_USUARIO       AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_TB_NX_CAMPO_ESTUDO      AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_TB_NX_INFLUENCIA_FAM    AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_TB_NX_NIVEL_EDUCACAO    AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_TB_NX_OCUPACAO          AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_TB_NX_DESCRICAO_CLIENTE AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SQ_T_NX_PREDICAO           AS BIGINT START WITH 1 INCREMENT BY 1;


/* ============================================================
   TABELAS
   ============================================================ */

---------------------------------------------------------------
-- TB_NX_USUARIO
---------------------------------------------------------------
CREATE TABLE TB_NX_USUARIO (
    id_usuario BIGINT PRIMARY KEY
        CONSTRAINT DF_TB_NX_USUARIO_ID DEFAULT NEXT VALUE FOR SQ_TB_NX_USUARIO,
    nm_cliente NVARCHAR(60) NOT NULL,
    nm_email   NVARCHAR(60) NOT NULL,
    nm_senha   NVARCHAR(20) NOT NULL
);


---------------------------------------------------------------
-- TB_NX_FUNCAO
---------------------------------------------------------------
CREATE TABLE TB_NX_FUNCAO (
    id_funcao BIGINT PRIMARY KEY
        CONSTRAINT DF_TB_NX_FUNCAO_ID DEFAULT NEXT VALUE FOR SQ_TB_NX_FUNCAO,
    nm_funcao NVARCHAR(30) NOT NULL
);


---------------------------------------------------------------
-- TB_FUNCAO_USUARIO (Many-To-Many)
---------------------------------------------------------------
CREATE TABLE TB_FUNCAO_USUARIO (
    id_funcao_usuario BIGINT PRIMARY KEY
        CONSTRAINT DF_TB_FUNCAO_USUARIO_ID DEFAULT NEXT VALUE FOR SQ_TB_FUNCAO_USUARIO,

    id_funcao  BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,

    CONSTRAINT FK_FUNCAO_USUARIO_FUNCAO
        FOREIGN KEY (id_funcao) REFERENCES TB_NX_FUNCAO(id_funcao),

    CONSTRAINT FK_FUNCAO_USUARIO_USUARIO
        FOREIGN KEY (id_usuario) REFERENCES TB_NX_USUARIO(id_usuario)
);


---------------------------------------------------------------
-- TB_NX_CAMPO_ESTUDO
---------------------------------------------------------------
CREATE TABLE TB_NX_CAMPO_ESTUDO (
    id_campo_estudo BIGINT PRIMARY KEY
        CONSTRAINT DF_TB_NX_CAMPO_ESTUDO_ID DEFAULT NEXT VALUE FOR SQ_TB_NX_CAMPO_ESTUDO,
    nm_campo_estudo NVARCHAR(30) NOT NULL
);


---------------------------------------------------------------
-- TB_NX_INFLUENCIA_FAM
---------------------------------------------------------------
CREATE TABLE TB_NX_INFLUENCIA_FAM (
    id_nivel_influencia BIGINT PRIMARY KEY
        CONSTRAINT DF_TB_NX_INFLUENCIA_FAM_ID DEFAULT NEXT VALUE FOR SQ_TB_NX_INFLUENCIA_FAM,
    nm_nivel_influencia NVARCHAR(20) NOT NULL
);


---------------------------------------------------------------
-- TB_NX_NIVEL_EDUCACAO
---------------------------------------------------------------
CREATE TABLE TB_NX_NIVEL_EDUCACAO (
    id_nivel_educacao BIGINT PRIMARY KEY
        CONSTRAINT DF_TB_NX_NIVEL_EDUCACAO_ID DEFAULT NEXT VALUE FOR SQ_TB_NX_NIVEL_EDUCACAO,
    nm_nivel_educacao NVARCHAR(30) NOT NULL
);


---------------------------------------------------------------
-- TB_NX_OCUPACAO
---------------------------------------------------------------
CREATE TABLE TB_NX_OCUPACAO (
    id_ocupacao BIGINT PRIMARY KEY
        CONSTRAINT DF_TB_NX_OCUPACAO_ID DEFAULT NEXT VALUE FOR SQ_TB_NX_OCUPACAO,
    nm_ocupacao NVARCHAR(30) NOT NULL
);


---------------------------------------------------------------
-- TB_NX_DESCRICAO_CLIENTE
---------------------------------------------------------------
CREATE TABLE TB_NX_DESCRICAO_CLIENTE (
    id_descricao BIGINT PRIMARY KEY
        CONSTRAINT DF_TB_NX_DESCRICAO_CLIENTE_ID DEFAULT NEXT VALUE FOR SQ_TB_NX_DESCRICAO_CLIENTE,

    id_usuario          BIGINT NOT NULL,
    id_nivel_educacao   BIGINT NOT NULL,
    id_nivel_influencia BIGINT NOT NULL,
    id_campo_estudo     BIGINT NOT NULL,
    id_ocupacao         BIGINT NOT NULL,

    qtde_anos_experiencia    INT NULL,
    ds_satisfacao            INT NULL,
    ds_tecnologia            INT NULL,
    ds_mudanca               INT NULL,
    ds_redes_profissionais   INT NULL,
    nr_idade                 INT NULL,
    dt_input                 DATETIME2 NOT NULL,
    ds_equilibrio            INT NULL,
    nr_salario               DECIMAL(15,2) NULL,
    ds_seguranca_trabalho    INT NULL,
    ds_mudanca_carreira      INT NULL,
    ds_competencia           INT NULL,
    ds_mentoria_disponivel   INT NULL,
    ds_certificacoes         INT NULL,
    ds_freelance             INT NULL,
    ds_mobilidade_geografica INT NULL,
    ds_genero                INT NULL,

    CONSTRAINT FK_DESCRICAO_USUARIO
        FOREIGN KEY (id_usuario) REFERENCES TB_NX_USUARIO(id_usuario),

    CONSTRAINT FK_DESCRICAO_NIVEL_EDUC
        FOREIGN KEY (id_nivel_educacao) REFERENCES TB_NX_NIVEL_EDUCACAO(id_nivel_educacao),

    CONSTRAINT FK_DESCRICAO_INFLUENCIA
        FOREIGN KEY (id_nivel_influencia) REFERENCES TB_NX_INFLUENCIA_FAM(id_nivel_influencia),

    CONSTRAINT FK_DESCRICAO_CAMPO_ESTUDO
        FOREIGN KEY (id_campo_estudo) REFERENCES TB_NX_CAMPO_ESTUDO(id_campo_estudo),

    CONSTRAINT FK_DESCRICAO_OCUPACAO
        FOREIGN KEY (id_ocupacao) REFERENCES TB_NX_OCUPACAO(id_ocupacao)
);



---------------------------------------------------------------
-- T_NX_PREDICAO
---------------------------------------------------------------
CREATE TABLE T_NX_PREDICAO (
    id_predicao BIGINT PRIMARY KEY
        CONSTRAINT DF_T_NX_PREDICAO_ID DEFAULT NEXT VALUE FOR SQ_T_NX_PREDICAO,

    id_descricao          BIGINT NOT NULL,
    ds_resultado_predicao INT NOT NULL,
    ds_recomendacao       NVARCHAR(40) NULL,

    CONSTRAINT FK_PREDICAO_DESCRICAO
        FOREIGN KEY (id_descricao) REFERENCES TB_NX_DESCRICAO_CLIENTE(id_descricao)
);

CREATE UNIQUE INDEX IX_T_NX_PREDICAO_ID_DESCRICAO
    ON T_NX_PREDICAO (id_descricao ASC);
