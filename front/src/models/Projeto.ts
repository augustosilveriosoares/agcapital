import Cliente from './Cliente';
import Atividade from './Atividade';
import Status from './Status';

export default class Projeto {
  id: string;
  nome: string;
  status: Status;
  cliente: Cliente;
  atividades: Atividade[];

  constructor(id: string, nome: string, status: Status, cliente: Cliente, atividades: Atividade[] = []) {
    this.id = id;
    this.nome = nome;
    this.status = status;
    this.cliente = cliente;
    this.atividades = atividades;
  }
}
