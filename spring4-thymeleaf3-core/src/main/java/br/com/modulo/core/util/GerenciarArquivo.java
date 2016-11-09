package br.com.modulo.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GerenciarArquivo {

	// instancia do Logger
	private static final Logger LOG = LoggerFactory.getLogger(GerenciarArquivo.class);

	private static final String ARQUIVO_CONFIG = "./config/config.properties";

	public static void lerArquivo(String url) {
		try {
			Reader r = new FileReader(ARQUIVO_CONFIG);
			LineNumberReader reader = new LineNumberReader(r);
			while ((url = reader.readLine()) != null) {
				if ("skin:".contains(url)) {
					System.out.println("local do arquivo lido: " + url);
				}
			}
			reader.close();
		} catch (IOException ex) {
			LOG.warn("lerArquivo(String url)", ex);
		}

	}

	public static Properties getProperties() {
		return getProperties(ARQUIVO_CONFIG);
	}

	public static Properties getProperties(String arquivoConfig) {
		Properties props = new Properties();
		try {
			Reader reader = new InputStreamReader(new FileInputStream(arquivoConfig), "UTF-8");
			props.load(reader);
		} catch (FileNotFoundException ex) {
			LOG.warn("Erro ao recuperar a propriedade do arquivo", ex);
		} catch (IOException ex) {
			LOG.warn("Erro ao recuperar a propriedade do arquivo", ex);
		}
		return props;
	}

	public static String getPropertie(String key) {
		return getProperties().getProperty(key);
	}

	public static void setProperties(Properties props) throws IOException {
		if (props != null) {
			Writer writer = new OutputStreamWriter(new FileOutputStream(ARQUIVO_CONFIG), "UTF-8");
			props.store(writer, "Arquivo de Configuração");
		}
	}

	/**
	 * Copia arquivos de um local para o outro
	 *
	 * @param origem
	 *            - Arquivo de origem
	 * @param destino
	 *            - Arquivo de destino
	 * @param overwrite
	 *            - Confirmação para sobrescrever os arquivos
	 * @throws IOException
	 */
	public static void copy(File origem, File destino, boolean overwrite) throws IOException {
		if (destino.exists() && !overwrite) {
			System.err.println(destino.getName() + " já existe, ignorando...");
			JOptionPane.showInputDialog(destino.getName() + " já existe, ignorando...");
			return;
		}
		FileInputStream fisOrigem = new FileInputStream(origem);
		FileOutputStream fisDestino = new FileOutputStream(destino);
		FileChannel fcOrigem = fisOrigem.getChannel();
		FileChannel fcDestino = fisDestino.getChannel();
		fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);
		fisOrigem.close();
		fisDestino.close();
	}

	/**
	 * Copia todos os arquivos de dentro de uma pasta para outra
	 *
	 * @param origem
	 *            - Diretório onde estão os arquivos a serem copiados
	 * @param destino
	 *            - Diretório onde os arquivos serão copiados
	 * @param overwrite
	 *            - Confirmação para sobrescrever os arquivos
	 * @throws IOException
	 */
	public static void copyAll(File origem, File destino, boolean overwrite) throws IOException {
		if (!destino.exists()) {
			destino.mkdir();
		}
		if (!origem.isDirectory()) {
			JOptionPane.showMessageDialog(null, "Origem deve ser um diretório");
			throw new UnsupportedOperationException();

		}
		if (!destino.isDirectory()) {
			JOptionPane.showMessageDialog(null, "Destino deve ser um diretório");
			throw new UnsupportedOperationException();
		}
		File[] files = origem.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				copyAll(file, new File(destino + "\\" + file.getName()), overwrite);
			} else {
				// "Copiando arquivo: " + file.getName());
				copy(file, new File(destino + "\\" + file.getName()), overwrite);
			}
		}
	}

}
