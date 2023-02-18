package modelo.logica;

import modelo.dao.EmpresaDAO;
import modelo.vo.EmpresaVO;

public class Empresa {
	private EmpresaDAO empresa;
	
	public void nuevaEmpresa(EmpresaVO empresa) {
		this.empresa=new EmpresaDAO();
		this.empresa.setEmpresa(empresa);
	}
}
