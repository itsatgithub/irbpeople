/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/Stopwatch.java,v 1.9 2005/02/16 18:03:04 P001001 Exp $
 * $Revision: 1.9 $
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
 * StopWatch for time measurement
 *
 * @author     <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version    $Revision: 1.9 $
 * @since      1.0
 */
public class Stopwatch {

	/**
	 * start time
	 */
	private long t1	= 0;

	/**
	 * stop time
	 */
	private long t2	= 0;


	/**
	 * Constructor
	 */
	public Stopwatch() {
		super();

		start();
	}

	/**
	 * Starts the Stopwatch
	 */
	public void start() {
		t1	= System.currentTimeMillis();
		t2	= 0;
	}

	/**
	 * Stops the Stopwatch
	 */
	public void stop() {
		t2	= System.currentTimeMillis();
	}

	/**
	 * Returns the Secounds between the start() and the stop()
	 *
	 * @return	String
	 */
	public String toString() {
		return (t2 - t1) + "ms";
	}
}
