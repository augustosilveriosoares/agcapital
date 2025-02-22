export default class Status {
    public static readonly EM_ANDAMENTO = new Status("Em Andamento");
    public static readonly FINALIZADO = new Status("Finalizado");
    public static readonly CANCELADO = new Status("Cancelado");
    public static readonly AGUARDANDO = new Status("Aguardando");

    id: string;
    descricao: string;

    constructor(descricao: string, id?: string) {
        this.descricao = descricao;

        this.id = id || null
    }


}