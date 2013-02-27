/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/Semaphore.java,v 1.8 2005/02/16 18:03:04 P001001 Exp $
 * $Revision: 1.8 $
 * $Date: 2005/02/16 18:03:04 $
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2005 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor URL : http://www.scc-gmbh.com
 * Product URL: http://www.common-controls.com
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SCC INFORMATIONSSYSTEME GMBH OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package com.cc.framework.common;

/**
 * Semaphore objekt by Dijkstra.
 * A semaphor is an object which defines two operations p() and v().
 * Internally a semaphore has a value an a state where processes can wait.
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.8 $
 * @since      1.0
 */
public class Semaphore {

	/**
	 * Der Wert des Semaphors gibt an:
	 * positiv: Anzahl der noch verfügbaren Betriebsmittel
	 * negativ: Anzahl der wartenden Prozesse
	 */
	private int counter	= 0;

	/**
	 * Constructor for Semaphore
	 */
	public Semaphore() {
		super();
	}

	/**
	 * Constructor for Semaphore
	 *
	 * @param	counter	Counter
	 */
	public Semaphore(int counter) {
		this.counter	= counter;
	}

	/**
	 * Die p()-Operation ist das eintrittsprotokoll des Semaphors.
	 * Der Prozeß welcher als erstes p() aufruft kann passieren
	 * (p von holländisch passeren)
	 *
	 * @throws	InterruptedException Thrown when a thread is waiting, sleeping, or otherwise paused for a long time and another thread interrupts it
	 */
	public void p() throws InterruptedException {
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}

		synchronized (this) {
			try {
				while (counter <= 0) {
					wait();
				}

				--counter;
			} catch (InterruptedException ie) {
				notify();
				throw ie;
			}
		}
	}

	/**
	 * Die v()-Operation ist das Austrittsprotokoll des Semaphors.
	 * V gibt den kritischen Abschnitt des Semaphors frei.
	 * (v von holländisch vrygeven)
	 *
	 * @throws	InterruptedException	Thrown when a thread is waiting, sleeping, or otherwise paused for a long time and another thread interrupts it
	 */
	public synchronized void v() throws InterruptedException {
		++counter;
		notify();
	}
}
