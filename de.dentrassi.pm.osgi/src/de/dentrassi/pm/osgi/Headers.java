package de.dentrassi.pm.osgi;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Headers
{
    private Headers ()
    {
    }

    public static List<AttributedValue> parseList ( final String string )
    {
        if ( string == null )
        {
            return null;
        }

        final List<AttributedValue> result = new LinkedList<> ();

        for ( final String tok : split ( string, ',' ) )
        {
            result.add ( parse ( tok ) );
        }

        return result;
    }

    public static AttributedValue parse ( final String string )
    {
        if ( string == null )
        {
            return null;
        }

        final String[] toks = segments ( string );

        if ( toks.length == 1 )
        {
            return new AttributedValue ( toks[0] );
        }
        else
        {
            return new AttributedValue ( toks[0], splitAttributes ( 1, toks ) );
        }
    }

    public static Map<String, String> splitAttributes ( final int skipToks, final String[] toks )
    {
        final Map<String, String> result = new HashMap<> ();

        for ( int i = skipToks; i < toks.length; i++ )
        {
            parseToken ( toks[i], result );
        }

        return result;
    }

    private static void parseToken ( final String v, final Map<String, String> result )
    {
        final StringBuilder key = new StringBuilder ();
        StringBuilder value = null;

        Character quote = null;

        int pos = 0;
        while ( pos < v.length () )
        {
            final char c = v.charAt ( pos );
            final Character next = pos + 1 < v.length () ? v.charAt ( pos + 1 ) : null;

            if ( quote == null )
            {
                if ( c == '"' )
                {
                    quote = c;
                }
                else if ( value != null )
                {
                    value.append ( c );
                }
                else if ( c == ':' && next != null && next.charValue () == '=' )
                {
                    value = new StringBuilder ();
                    pos++; // skip one char
                }
                else if ( c == '=' )
                {
                    value = new StringBuilder ();
                }
                else
                {
                    key.append ( c );
                }
            }
            else
            {
                if ( c == quote )
                {
                    quote = null;
                }
                else if ( value != null )
                {
                    value.append ( c );
                }
                else
                {
                    key.append ( c );
                }

            }
            pos++;
        }

        // return result

        if ( key.length () <= 0 )
        {
            return;
        }

        if ( value != null )
        {
            result.put ( key.toString (), value.toString () );
        }
        else
        {
            final String kv = key.toString ();
            result.put ( kv, kv );
        }
    }

    public static String[] segments ( final String string )
    {
        return split ( string, ';' );
    }

    public static String[] split ( final String string, final char delimiter )
    {
        final List<String> result = new LinkedList<String> ();

        int pos = 0;

        Character quote = null;
        StringBuilder sb = new StringBuilder ();
        while ( pos < string.length () )
        {
            final char c = string.charAt ( pos );
            if ( quote == null )
            {
                if ( c == '"' )
                {
                    quote = c;
                    sb.append ( c );
                }
                else if ( Character.isWhitespace ( c ) )
                {
                    // ignore
                }
                else if ( c == delimiter )
                {
                    result.add ( sb.toString () );
                    sb = new StringBuilder ();
                }
                else
                {
                    sb.append ( c );
                }
            }
            else
            {
                // quoted
                if ( c == quote )
                {
                    quote = null;
                }
                sb.append ( c );
            }

            pos++;
        }

        if ( sb.length () > 0 )
        {
            result.add ( sb.toString () );
        }

        return result.toArray ( new String[result.size ()] );
    }

}
