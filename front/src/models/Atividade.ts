import Projeto from "@/models/Projeto";
import {StatusEnum} from "@/utils/StatusEnum";

export default class Atividade {

    id:string;
    descricao:string;
    projeto:Projeto;
    status:StatusEnum;

}