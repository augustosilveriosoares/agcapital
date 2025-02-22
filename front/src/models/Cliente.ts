import Projeto from './Projeto';

export default class Cliente {
  id: string;
  nome: string;
  email: string;
  projetos: Projeto[];

  constructor(id: string, nome: string, email: string, projetos: Projeto[] = []) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.projetos = projetos;
  }



}
