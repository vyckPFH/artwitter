//JOIN exemplo:

//JOIN entre tabelas    
    public LinkedList<Contato> tentativaJoin() {
        LinkedList<Contato> contatos = new LinkedList<>();
        String sqlContato = ("SELECT * FROM contatos JOIN enderecos ON enderecos.id = id_enderecos;"); 
        Connection cow = ConnectionFactory.getConnection(); // Abre conexão p sql

        try {
            PreparedStatement psContato = cow.prepareStatement(sqlContato);
            ResultSet rs = psContato.executeQuery();

            while (rs.next()){
                Endereco end = new Endereco();
                end.setId(rs.getInt("id_endereco"));
                end.setRua(rs.getString("rua"));
                end.setNumero(rs.getString("numero"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));

                Contato cont = new Contato();
                cont.setId(rs.getInt("id"));
                cont.setNome(rs.getString("nome"));
                cont.setCelular(rs.getString("celular"));
                cont.setEmail(rs.getString("email"));
                cont.setEndereco(end);
                contatos.add(cont);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contatos;
    }
