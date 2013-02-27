/*
 * $Header: d:/repository/cvs/cc-framework/source/com/cc/framework/common/Algorithm.java,v 1.12 2005/10/17 17:09:21 P001002 Exp $
 * $Revision: 1.12 $
 * $Date: 2005/10/17 17:09:21 $
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
 * Interface for an algorythm which should be processed for all elements in a
 * collection.
 * 
 * @author <a href="mailto:hschulz@scc-gmbh.com">Harald Schulz</a>
 * @version $Revision: 1.12 $
 * @since 1.0
 */
public interface Algorithm {

	/**
	 * Completes the algorithm for an element in the collection.
	 * 
	 * @param uniqueId
	 *            Id
	 * @param obj
	 *            Object
	 * @return boolean Returns true if the algorithm should also be processed
	 *         for the other elements in the collection or false if the
	 *         processing should be stoped.
	 * @throws Exception
	 *             Is thrown in case of an processing error
	 */
	public boolean execute(String uniqueId, Object obj) throws Exception;
}