package api.adocao.entidade;

public enum StatusRequisicaoEnum {
    PENDENTE,
    EM_ANALISE,
    NEGADO,
    CANCELADO,
    ACEITO;

    public static boolean isValid(String test){
        try {
            StatusRequisicaoEnum status = StatusRequisicaoEnum.valueOf(test);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
