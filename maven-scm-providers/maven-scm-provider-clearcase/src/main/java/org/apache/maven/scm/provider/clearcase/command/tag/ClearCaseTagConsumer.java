package org.apache.maven.scm.provider.clearcase.command.tag;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.ScmFile;
import org.apache.maven.scm.ScmFileStatus;
import org.codehaus.plexus.util.cli.StreamConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:trygvis@inamo.no">Trygve Laugst&oslash;l</a>
 * @version 
 */
public class ClearCaseTagConsumer
    implements StreamConsumer
{
    private ScmLogger logger;

    private List taggedFiles = new ArrayList();

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public ClearCaseTagConsumer( ScmLogger logger )
    {
        this.logger = logger;
    }

    // ----------------------------------------------------------------------
    // Stream Consumer Implementation
    // ----------------------------------------------------------------------

    public void consumeLine( String line )
    {
        logger.debug( line );
        int beginIndexTag = line.indexOf( '"' );
        int endIndexTag = line.indexOf( '"', beginIndexTag + 1 );
        int beginIndex = line.indexOf( '"', endIndexTag + 1 );
        String fileName = line.substring( beginIndex + 1, line.indexOf( '"', beginIndex + 1 ) );
        taggedFiles.add( new ScmFile( fileName, ScmFileStatus.TAGGED ) );
    }

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public List getTaggedFiles()
    {
        return taggedFiles;
    }
}