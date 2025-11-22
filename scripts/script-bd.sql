/* ============================================================
   DROP TABLES (na ordem certa)
   ============================================================ */

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
   DROP SEQUENCES (se existirem)
   ============================================================ */

IF OBJECT_ID('dbo.SEQ_TB_NX_USUARIO', 'SO') IS NOT NULL DROP SEQUENCE dbo.SEQ_TB_NX_USUARIO;
IF OBJECT_ID('dbo.SEQ_TB_NX_FUNCAO', 'SO') IS NOT NULL DROP SEQUENCE dbo.SEQ_TB_NX_FUNCAO;
IF OBJECT_ID('dbo.SEQ_TB_FUNCAO_USUARIO', 'SO') IS NOT NULL DROP SEQUENCE dbo.SEQ_TB_FUNCAO_USUARIO;
IF OBJECT_ID('dbo.SEQ_TB_NX_CAMPO_ESTUDO', 'SO') IS NOT NULL DROP SEQUENCE dbo.SEQ_TB_NX_CAMPO_ESTUDO;
IF OBJECT_ID('dbo.SEQ_TB_NX_INFLUENCIA_FAM', 'SO') IS NOT NULL DROP SEQUENCE dbo.SEQ_TB_NX_INFLUENCIA_FAM;
IF OBJECT_ID('dbo.SEQ_TB_NX_NIVEL_EDUCACAO', 'SO') IS NOT NULL DROP SEQUENCE dbo.SEQ_TB_NX_NIVEL_EDUCACAO;
IF OBJECT_ID('dbo.SEQ_TB_NX_OCUPACAO', 'SO') IS NOT NULL DROP SEQUENCE dbo.SEQ_TB_NX_OCUPACAO;
IF OBJECT_ID('dbo.SEQ_TB_NX_DESCRICAO_CLIENTE', 'SO') IS NOT NULL DROP SEQUENCE dbo.SEQ_TB_NX_DESCRICAO_CLIENTE;
IF OBJECT_ID('dbo.SEQ_T_NX_PREDICAO', 'SO') IS NOT NULL DROP SEQUENCE dbo.SEQ_T_NX_PREDICAO;


/* ============================================================
   SEQUENCES  (NOMES ALINHADOS COM O JAVA)
   ============================================================ */

CREATE SEQUENCE dbo.SEQ_TB_NX_USUARIO           AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE dbo.SEQ_TB_NX_FUNCAO            AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE dbo.SEQ_TB_FUNCAO_USUARIO       AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE dbo.SEQ_TB_NX_CAMPO_ESTUDO      AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE dbo.SEQ_TB_NX_INFLUENCIA_FAM    AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE dbo.SEQ_TB_NX_NIVEL_EDUCACAO    AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE dbo.SEQ_TB_NX_OCUPACAO          AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE dbo.SEQ_TB_NX_DESCRICAO_CLIENTE AS BIGINT START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE dbo.SEQ_T_NX_PREDICAO           AS BIGINT START WITH 1 INCREMENT BY 1;


/* ============================================================
   TABELAS
   ============================================================ */

---------------------------------------------------------------
-- TB_NX_USUARIO  (ajustado pros @Column do Java)
---------------------------------------------------------------
CREATE TABLE dbo.TB_NX_USUARIO (
    id_usuario BIGINT NOT NULL
        CONSTRAINT DF_TB_NX_USUARIO_ID DEFAULT NEXT VALUE FOR dbo.SEQ_TB_NX_USUARIO,
    nm_cliente NVARCHAR(80) NOT NULL,   -- Java usa length = 80
    nm_email   NVARCHAR(80) NOT NULL,   -- Java usa length = 80
    nm_senha   NVARCHAR(100) NOT NULL,  -- Java usa length = 100 (para hash)
    CONSTRAINT PK_TB_NX_USUARIO PRIMARY KEY (id_usuario)
);

---------------------------------------------------------------
-- TB_NX_FUNCAO
---------------------------------------------------------------
CREATE TABLE dbo.TB_NX_FUNCAO (
    id_funcao BIGINT NOT NULL
        CONSTRAINT DF_TB_NX_FUNCAO_ID DEFAULT NEXT VALUE FOR dbo.SEQ_TB_NX_FUNCAO,
    nm_funcao NVARCHAR(30) NOT NULL,
    CONSTRAINT PK_TB_NX_FUNCAO PRIMARY KEY (id_funcao)
);

---------------------------------------------------------------
-- TB_FUNCAO_USUARIO (Many-To-Many)
---------------------------------------------------------------
CREATE TABLE dbo.TB_FUNCAO_USUARIO (
    id_funcao_usuario BIGINT NOT NULL
        CONSTRAINT DF_TB_FUNCAO_USUARIO_ID DEFAULT NEXT VALUE FOR dbo.SEQ_TB_FUNCAO_USUARIO,
    id_funcao  BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    CONSTRAINT PK_TB_FUNCAO_USUARIO PRIMARY KEY (id_funcao_usuario),
    CONSTRAINT FK_FUNCAO_USUARIO_FUNCAO
        FOREIGN KEY (id_funcao) REFERENCES dbo.TB_NX_FUNCAO(id_funcao),
    CONSTRAINT FK_FUNCAO_USUARIO_USUARIO
        FOREIGN KEY (id_usuario) REFERENCES dbo.TB_NX_USUARIO(id_usuario)
);

---------------------------------------------------------------
-- TB_NX_CAMPO_ESTUDO
---------------------------------------------------------------
CREATE TABLE dbo.TB_NX_CAMPO_ESTUDO (
    id_campo_estudo BIGINT NOT NULL
        CONSTRAINT DF_TB_NX_CAMPO_ESTUDO_ID DEFAULT NEXT VALUE FOR dbo.SEQ_TB_NX_CAMPO_ESTUDO,
    nm_campo_estudo NVARCHAR(30) NOT NULL,
    CONSTRAINT PK_TB_NX_CAMPO_ESTUDO PRIMARY KEY (id_campo_estudo)
);

---------------------------------------------------------------
-- TB_NX_INFLUENCIA_FAM
---------------------------------------------------------------
CREATE TABLE dbo.TB_NX_INFLUENCIA_FAM (
    id_nivel_influencia BIGINT NOT NULL
        CONSTRAINT DF_TB_NX_INFLUENCIA_FAM_ID DEFAULT NEXT VALUE FOR dbo.SEQ_TB_NX_INFLUENCIA_FAM,
    nm_nivel_influencia NVARCHAR(20) NOT NULL,
    CONSTRAINT PK_TB_NX_INFLUENCIA_FAM PRIMARY KEY (id_nivel_influencia)
);

---------------------------------------------------------------
-- TB_NX_NIVEL_EDUCACAO
---------------------------------------------------------------
CREATE TABLE dbo.TB_NX_NIVEL_EDUCACAO (
    id_nivel_educacao BIGINT NOT NULL
        CONSTRAINT DF_TB_NX_NIVEL_EDUCACAO_ID DEFAULT NEXT VALUE FOR dbo.SEQ_TB_NX_NIVEL_EDUCACAO,
    nm_nivel_educacao NVARCHAR(30) NOT NULL,
    CONSTRAINT PK_TB_NX_NIVEL_EDUCACAO PRIMARY KEY (id_nivel_educacao)
);

---------------------------------------------------------------
-- TB_NX_OCUPACAO
---------------------------------------------------------------
CREATE TABLE dbo.TB_NX_OCUPACAO (
    id_ocupacao BIGINT NOT NULL
        CONSTRAINT DF_TB_NX_OCUPACAO_ID DEFAULT NEXT VALUE FOR dbo.SEQ_TB_NX_OCUPACAO,
    nm_ocupacao NVARCHAR(30) NOT NULL,
    CONSTRAINT PK_TB_NX_OCUPACAO PRIMARY KEY (id_ocupacao)
);

---------------------------------------------------------------
-- TB_NX_DESCRICAO_CLIENTE
---------------------------------------------------------------
CREATE TABLE dbo.TB_NX_DESCRICAO_CLIENTE (
    id_descricao BIGINT NOT NULL
        CONSTRAINT DF_TB_NX_DESCRICAO_CLIENTE_ID DEFAULT NEXT VALUE FOR dbo.SEQ_TB_NX_DESCRICAO_CLIENTE,

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

    CONSTRAINT PK_TB_NX_DESCRICAO_CLIENTE PRIMARY KEY (id_descricao),

    CONSTRAINT FK_DESCRICAO_USUARIO
        FOREIGN KEY (id_usuario)        REFERENCES dbo.TB_NX_USUARIO(id_usuario),
    CONSTRAINT FK_DESCRICAO_NIVEL_EDUC
        FOREIGN KEY (id_nivel_educacao) REFERENCES dbo.TB_NX_NIVEL_EDUCACAO(id_nivel_educacao),
    CONSTRAINT FK_DESCRICAO_INFLUENCIA
        FOREIGN KEY (id_nivel_influencia) REFERENCES dbo.TB_NX_INFLUENCIA_FAM(id_nivel_influencia),
    CONSTRAINT FK_DESCRICAO_CAMPO_ESTUDO
        FOREIGN KEY (id_campo_estudo)   REFERENCES dbo.TB_NX_CAMPO_ESTUDO(id_campo_estudo),
    CONSTRAINT FK_DESCRICAO_OCUPACAO
        FOREIGN KEY (id_ocupacao)       REFERENCES dbo.TB_NX_OCUPACAO(id_ocupacao)
);

---------------------------------------------------------------
-- T_NX_PREDICAO
---------------------------------------------------------------
CREATE TABLE dbo.T_NX_PREDICAO (
    id_predicao BIGINT NOT NULL
        CONSTRAINT DF_T_NX_PREDICAO_ID DEFAULT NEXT VALUE FOR dbo.SEQ_T_NX_PREDICAO,

    id_descricao          BIGINT NOT NULL,
    ds_resultado_predicao INT NOT NULL,
    ds_recomendacao       NVARCHAR(40) NULL,

    CONSTRAINT PK_T_NX_PREDICAO PRIMARY KEY (id_predicao),

    CONSTRAINT FK_PREDICAO_DESCRICAO
        FOREIGN KEY (id_descricao) REFERENCES dbo.TB_NX_DESCRICAO_CLIENTE(id_descricao)
);

CREATE UNIQUE INDEX IX_T_NX_PREDICAO_ID_DESCRICAO
    ON dbo.T_NX_PREDICAO (id_descricao ASC);
