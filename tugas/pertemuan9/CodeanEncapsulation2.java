class CodeanEncapsulation2{
	public static void main(String []args){
		RekeningBank rekening = new RekeningBank("123456789","john doe", 1000.0);
		rekening.setorUang(500.0);
		rekening.tarikUang(200.0);

		double saldoSekarang = rekening.getSaldo();

		System.out.println(saldoSekarang);		
	}
}

class RekeningBank {
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;

	public RekeningBank(String nomorRekening, String namaPemilik, double saldo){
		this.nomorRekening = nomorRekening;
		this.namaPemilik = namaPemilik;
		this.saldo = saldo;
	}

	public void setorUang(double jumlah){
		saldo += jumlah;
	}

	public void tarikUang(double jumlah){
		if(saldo >= jumlah){
			saldo -= jumlah;
		} else {
			System.out.println("Saldo tidak mencukupi");
		}
	}

	public double getSaldo(){
		return saldo;
	}

	public void setSaldo(double saldo){
		this.saldo = saldo;
	}
}

